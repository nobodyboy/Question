package com.starry.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;

public class StreamDecoder extends Reader
{
    // 默认字节缓冲区大小，最小是32，最大是8192个字节
    private static final int MIN_BYTE_BUFFER_SIZE = 32;
    private static final int DEFAULT_BYTE_BUFFER_SIZE = 8192;

    // 确保流是被打开状态否则不能输入输出
    private volatile boolean isOpen = true;

    private void ensureOpen() throws IOException
    {
        if (!isOpen)
            throw new IOException("Stream closed");
    }

    // 本意是指一次最少读入2个字符如果是返回一个字符，那么另外一个字符就会存在leftoverChar中待后续操作按返回
    // 特别为read准备，read方法要求返回一个字符，但是实际是读取2个字符，剩余1个字符会被赋予给leftoverChar变量，而haveLeftoverChar用于判断
    private boolean haveLeftoverChar = false;
    private char leftoverChar;

    // 字符集名称
    private Charset cs;

    // 解码器
    private CharsetDecoder decoder;

    // 字节缓冲对象
    private ByteBuffer bb;

    // 底层字节输入流
    private InputStream in;

    // 信道
    private ReadableByteChannel ch;

    // 根据字符集名称得到字符集对象并构造StreamDecoder对象
    public static StreamDecoder forInputStreamReader(InputStream in,
            Object lock, String charsetName)
            throws UnsupportedEncodingException
    {
        String csn = charsetName;
        if (csn == null)
            csn = Charset.defaultCharset().name();
        try
        {
            if (Charset.isSupported(csn))
                return new StreamDecoder(in, lock, Charset.forName(csn));
        }
        catch (IllegalCharsetNameException x)
        {
        }
        throw new UnsupportedEncodingException(csn);
    }

    // 构造函数，本质是调用下面的方法
    StreamDecoder(InputStream in, Object lock, Charset cs)
    {
        this(in, lock, cs.newDecoder()
                .onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE));
    }

    // 构造函数，初始化实例域，可以看出字节缓冲对象的大小就是默认值，无法指定
    StreamDecoder(InputStream in, Object lock, CharsetDecoder dec)
    {
        super(lock);
        this.cs = dec.charset();
        this.decoder = dec;

        // 此路径禁用，直到直接缓冲区更快？未理解
        if (false && in instanceof FileInputStream)
        {
            ch = getChannel((FileInputStream) in);
            if (ch != null)
                bb = ByteBuffer.allocateDirect(DEFAULT_BYTE_BUFFER_SIZE);
        }
        if (ch == null)
        {
            this.in = in;
            this.ch = null;
            bb = ByteBuffer.allocate(DEFAULT_BYTE_BUFFER_SIZE);
        }
        bb.flip(); // 清空字符缓冲对象，保证最开始是空的
    }

    // 读取方法，返回单个字符，实际调用下面的read0()方法
    public int read() throws IOException
    {
        return read0();
    }

    // 返回单个字符
    private int read0() throws IOException
    {
        synchronized (lock)
        {

            // 如果缓存条件为真，代表上次读取到了2个字符，剩余一个字符，那么直接返回即可
            if (haveLeftoverChar)
            {
                haveLeftoverChar = false;
                return leftoverChar;
            }

            // 转换更多字节，跟实例域介绍一致，尽量最少读取2个字符
            char cb[] = new char[2];
            int n = read(cb, 0, 2); // 调用下面方法，返回读取的字符个数
            switch (n)
            {
            case -1: // 若返回-1.代表读到末尾
                return -1;
            case 2: // 代表读取到2个字符，但是方法要求返回一个字符，因此第二个字符就赋予变量暂时缓存
                leftoverChar = cb[1];
                haveLeftoverChar = true; // 代表剩余一个字符存在缓冲变量中
            case 1: // 代表读取到1个字符，直接返回
                return cb[0];
            default:
                assert false : n;
                return -1;
            }
        }
    }

    // 读取最多length个字符到字符数组cbuf中，cbuf从offset下标开始存储，返回实际读取的字符数
    public int read(char cbuf[], int offset, int length) throws IOException
    {
        int off = offset;
        int len = length;
        synchronized (lock)
        {
            ensureOpen();
            if ((off < 0) || (off > cbuf.length) || (len < 0)
                    || ((off + len) > cbuf.length) || ((off + len) < 0))
            {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0)
                return 0;

            int n = 0;

            if (haveLeftoverChar)
            { // 如果上次read()方法中剩余1个字符还未被取出

                cbuf[off] = leftoverChar; // 那么剩余的这个字符直接赋予到数中
                off++;
                len--; // 要读取的length个字节-1。下标+1.代表off位置已存储上次read()方法剩余的那个字符了
                haveLeftoverChar = false; // 取完后设置条件为fasle,避免下次操作取空字符出来
                n = 1;
                if ((len == 0) || !implReady()) // 如果length就是1的话，那么取完剩余字符就可以返回终止方法了。如果lengh不是1，那么就可以继续往下执行

                    return n;
            }

            if (len == 1)
            { // 若len为1，则代表此时最多在读取1个字符到数组中。直接调用read()方法读取字符即可

                int c = read0();
                if (c == -1) // 若返回-1，则代表读到末尾，那么存在两种情况，一种是n为0,说明文件从开始就是到末尾了的。若n不为0，则说明经历了上个if条件，是读取了一个字符到数组中的。
                    return (n == 0) ? -1 : n;
                cbuf[off] = (char) c; // 若不为-1，则代表了读取到了字符，因此存入到字符数组即可
                return n + 1;
            }

            // 若len为1时则前面就已经返回，若为2时，存在剩余字符，则前面也已经返回，不存在剩余字符，则执行后面代码，若为3时，则该代码必定执行
            return n + implRead(cbuf, off, off + len);
        }
    }
    
    //从下标off开始，到下标end，共end-off个字符到cbuf数组中，注意这里的end不是个数，而是代表了下标的意思
    int implRead(char[] cbuf, int off, int end) throws IOException
    {

        // 为了处理代理对，该方法要求调用者至少尝试读取2个字符，将额外的字符（如果有）存在跟高级别比在此处处理更容易
        
        assert (end - off > 1);  //

        CharBuffer cb = CharBuffer.wrap(cbuf, off, end - off); //将字符数组包装到缓冲区中，缓冲区修改，字符数组也会被修改，cb本质理解为一个数组，当前位置为off,界限为end
        if (cb.position() != 0)
            cb = cb.slice();   //确保cb[0]=cbuf[0ff]

        boolean eof = false;  //代表着还有其他的字节输入提供
        for (;;)
        {
            CoderResult cr = decoder.decode(bb, cb, eof);  //将字节缓冲区bb中解码尽可能多的字节，将结果写入给定的字符缓冲区cb中
            if (cr.isUnderflow())        // 代表字节缓冲区内容已全部解码放入到字符缓冲区中
            {
                if (eof)
                    break;
                if (!cb.hasRemaining())  //cb.hasRemaining()代表是否还有空间剩余，若没有则跳出循环
                    break;
                if ((cb.position() > 0) && !inReady()) //若字节缓冲区全部输入，字符缓冲区还未读满时，返回读取到的字符数，此行非常重要，没有此行，将永远在循环中出不来了
                    break; 
                int n = readBytes();  //继续读取字节到字节缓冲区bb中
                if (n < 0)    //若为-1，代表着已到末尾，则不会有其它输入了
                {
                    eof = true;
                    if ((cb.position() == 0) && (!bb.hasRemaining())) //若字节缓冲区bb没有剩余空间且字符缓冲区cb的当前位置为0，则跳出循环
                        break;
                    decoder.reset();  //重置解码器.清除所有内部状态。 
                }
                continue;     //代表着刚才的读取有内容。继续进行解码转换
            }
            if (cr.isOverflow())        //代表着字符缓冲区已满，应该使用未满的字符缓冲区在次调用该方法
            {
                assert cb.position() > 0;
                break;
            }
            cr.throwException();
        }

        if (eof)  //代表没有输入了
        {
            // ## Need to flush decoder
            decoder.reset();   //重置解码器，清楚状态
        }

        if (cb.position() == 0)
        {
            if (eof)
                return -1;
            assert false;
        }
        return cb.position();  //返回字符缓冲区的当前位置，则代表了转换后的字符个数---但是有个问题啊。并没有把字符从缓冲区对象
    }
    
    //最终调用的方法，利用底层字节输入流，读取字节到字节缓冲区中
    private int readBytes() throws IOException
    {
        bb.compact(); //压缩缓冲区,当缓冲区中当前位置已到界限时，则时当前位置归0，界限位置到容量位置
        try
        {
            if (ch != null)
            {
                // Read from the channel
                int n = ch.read(bb);
                if (n < 0)
                    return n;
            }
            else
            {
                // 从输入流中读取，然后更新缓冲区
                int lim = bb.limit();
                int pos = bb.position();
                assert (pos <= lim);
                int rem = (pos <= lim ? lim - pos : 0);
                assert rem > 0;
                int n = in.read(bb.array(), bb.arrayOffset() + pos, rem);
                if (n < 0)
                    return n;
                if (n == 0)
                    throw new IOException(
                            "Underlying input stream returned zero bytes");
                assert (n <= rem) : "n = " + n + ", rem = " + rem;
                bb.position(pos + n);
            }
        }
        finally
        {
            
            bb.flip();   //反转缓冲区，即limit位置位于position.position归于0
        }

        int rem = bb.remaining(); //读取到的字节数
        assert (rem != 0) : rem;
        return rem;
    }

   

    /**
     * 关闭资源
     */
    public void close() throws IOException
    {
        synchronized (lock)
        {
            if (!isOpen)
                return;
            implClose();
            isOpen = false;
        }
    }

    void implClose() throws IOException
    {
        if (ch != null)
            ch.close();
        else
            in.close();
    }

  

    // 检测是否堵塞或者字节缓冲区是否还有空间
    boolean implReady()
    {
        return bb.hasRemaining() || inReady();
    }

    private boolean inReady()
    {
        try
        {
            return (((in != null) && (in.available() > 0)) || (ch instanceof FileChannel)); // ##
                                                                                            // RBC.available()?
        }
        catch (IOException x)
        {
            return false;
        }
    }

    // 检测是否准备好
    public boolean ready() throws IOException
    {
        synchronized (lock)
        {
            ensureOpen();
            return haveLeftoverChar || implReady();
        }
    }

    // 检测流是否打开
    private boolean isOpen()
    {
        return isOpen;
    }

    // 在构建的早期阶段，我们尚未构建NIO本机代码，所以通过捕获第一个UnsatisfiedLinkError来防范它并设置此标志，以便以后的尝试快速失败。

    private static volatile boolean channelsAvailable = true;

    private static FileChannel getChannel(FileInputStream in)
    {
        if (!channelsAvailable)
            return null;
        try
        {
            return in.getChannel();
        }
        catch (UnsatisfiedLinkError x)
        {
            channelsAvailable = false;
            return null;
        }
    }
}
