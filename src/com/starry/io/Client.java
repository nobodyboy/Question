package com.starry.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Client {

	/**
	 * 文件输入字符流
	 * @throws IOException
	 */
	static void fileInputStreamTest() throws IOException {
		FileInputStream fis = new FileInputStream("file/fis.txt");
		byte[] fisByte = new byte[100];
		int readNum = 0;
		
		while ((readNum = fis.read(fisByte)) > 0) {
			System.out.println(new String(fisByte, 0, readNum));
			
		}
		fis.close();
	}
	
	/**
	 * 文件输入字节流
	 * @throws IOException
	 */
	static void fileReaderTest() throws IOException {
		try {
			FileReader fr = new FileReader("file/fis.txt");
			char[] frChar = new char[100];
			int readNum = 0;
			
			while ((readNum = fr.read(frChar)) > 0) {
				System.out.println(new String(frChar, 0, readNum));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件输出字符流
	 * @throws IOException
	 */
	static void fileOutputStreamTest() throws IOException {
		try {
			FileInputStream fis = new FileInputStream("file/fis.txt");
			FileOutputStream fos = new FileOutputStream("file/fos.txt");
			byte[] fisByte = new byte[100];
			int readNum = 0;
			
			while((readNum = fis.read(fisByte)) > 0) {
				fos.write(fisByte, 0, readNum);
			}
			System.out.println("write success!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件输出字节流
	 * @throws IOException
	 */
	static void fileWriteTest() throws IOException {
		FileWriter fw = new FileWriter("file/fw.txt");
		fw.write("我爱你");
		fw.write("中国");
		
		fw.close();
	}
	
	static void printStream() throws IOException {
		FileOutputStream fos = new FileOutputStream("file/ps.txt");
		PrintStream ps = new PrintStream(fos);
		ps.print(new Client());
		
		ps.close();
		fos.close();
	}
	
	static void stringReadWrite() throws IOException {
		String str = "我爱你中国!";
		char[] srChar = new char[30];
		int charNum = 0;
		
		StringReader sr = new StringReader(str);
		while ((charNum = sr.read(srChar)) > 0) {
			System.out.println(new String(srChar, 0, charNum));
		}
		sr.close();
		
		StringWriter sw = new StringWriter();
		sw.write("我爱我的家乡!");
		sw.write("我也爱我的祖国!");
		System.out.println(sw.toString());
		sw.close();
		
	}
	
	static void inputStreamRead() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String lineStr= null;
		while ((lineStr = br.readLine())!=null) {
			if ("over".equals(lineStr)) {
				break;
			}
			System.out.println(lineStr);
		}
		isr.close();
		br.close();
	}
	
	static void stringReader() throws IOException {
		String test = "hello word";
		StringReader srReader = new StringReader(test);
		char[] charRead = new char[test.length()];
		int firstChar;
		while ((firstChar = srReader.read()) > 0) {
			charRead[0] = (char)firstChar;
			int num = srReader.read(charRead, 1, test.length()-1);
			System.out.println(num);   // 9
			System.out.println(new String(charRead));  // hello word
		}
		srReader.read();
	}
	
	static void inputStreamReader() throws IOException {
		InputStreamReader isReader = new InputStreamReader(new FileInputStream("file/fis.txt"));
		char[] charReader = new char[100];
		while(isReader.read() > 0) {
			isReader.read(charReader);
			System.out.println(new String(charReader));
			System.out.println();
		}
	}
	
	static void bufferedReader() throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("file/fis.txt")));
		do {
			System.out.println(bReader.readLine());
		}while(bReader.readLine() != null);
		
	}
	
	static void stringWriter() {
		String test = "hello world";
		StringWriter sWriter = new StringWriter();
		sWriter.write(test);
		System.out.println(sWriter.toString());   // hello world
	}
	
	static void outputStreamWriter() throws IOException {
		OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream("file/osWriter.txt"));
		String test = "hello world";
		osWriter.write(test);
		osWriter.close();
	}
	
	static void bufferWriter() throws IOException {
		BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("file/bWriter.txt")));
		String test = "test test test test";
		bWriter.write(test);
		bWriter.close();
	}
	
	public static void main(String[] args) {
		try {
//			fileInputStreamTest();
//			fileReaderTest();
//			fileOutputStreamTest();
//			fileWriteTest();
//			printStream();
//			stringReadWrite();
//			stringReadWrite();
//			inputStreamRead();
//			stringReader();
//			inputStreamReader();
//			bufferedReader();
			
//			bufferedWriter();
			bufferWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//stringWriter();
	}
	
}
