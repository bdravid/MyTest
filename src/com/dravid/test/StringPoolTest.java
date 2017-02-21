package com.dravid.test;

/**
 * Created by Bhushan on 04/06/2016.
 */
public class StringPoolTest {
        public static void main(String[] args)
        {
            String hello = "Hello", lo = "lo";
            System.out.print((Other1.hello == hello) + " ");     //line 1
            System.out.print((Other1.hello == "Hello") + " ");   //line 2
            System.out.print((hello == ("Hel"+"lo")) + " ");       //line 3
            System.out.print((hello == ("Hel"+lo)) + " ");         //line 4
            System.out.println(hello == ("Hel"+lo).intern());      //line 5
            System.out.println(("Hel"+lo).hashCode());   //hashcode is 69609650 (machine depedent)
            System.out.println("Hello".hashCode());       //hashcode is same WHY ??.
        }

    static class Other1 { static String hello = "Hello"; }
}
