package com.starry.String1;

public class StringTest {
    public static void main(String[] args) {

        String str = "hello";
        final String str1 = "hello";

        String str2 = "helloworld";
        String str3 = str1 + "world";
        String str4 = str + "world";

        System.out.println(str == str1);   // t
        System.out.println(str2 == str3);   // t
        System.out.println(str2 == str4);   // f
    }
}