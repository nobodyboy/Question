package com.starry.serializable1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.print.PaperSource;

public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 序列化
        FileOutputStream fos = new FileOutputStream("e://out.class");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person person = new Person("starry", 25, "177");
        oos.writeObject(person);
        oos.flush();
        oos.close();
        System.out.println(person.slave);
        Person.slave = 10;
        
        //反序列化
        FileInputStream fis = new FileInputStream("e://out.class");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person personOut = (Person) ois.readObject();
        System.out.println(personOut.getName()+ " " + personOut.getAge() + " " + personOut.getHeight());
        System.out.println(personOut.slave);
        
        Person.slave = 20;
        System.out.println(personOut.slave);
      
//        //字节byte读取
//        FileInputStream fis1 = new FileInputStream("e://out.class");
//        //将文件数据读取到字节数组byte中,数组大小由fis1的可读大小决定
//        byte[] bytes = new byte[fis1.available()];
//        while( fis1.read(bytes) != -1){}
//        //确定十六进制的书写方式
//        String HEX = "0123456789ABCDEF";
//        //将字节转化为十六进制
//        for(byte b:bytes){
//            //取字节的高四位,与0x0f与运算,得到该十六进制数据对应的索引(0~15)
//            System.out.print(HEX.charAt((b >> 4) & 0x0f));
//            //字节的低四位
//            System.out.print(HEX.charAt(b & 0x0f));
//            System.out.print(" ");//AC ED ......
//        }
//        fis.close();
	}
}
