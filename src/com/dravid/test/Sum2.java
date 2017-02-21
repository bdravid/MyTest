package com.dravid.test;

/**
 * Created by Bhushan on 06/07/2016.
 */
public class Sum2 {
    public static void main(String[] args) {
        sum("111", "11");
    }

    public static void sum(String first, String second){
        int a = first.length() - 1;
        int b = second.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (a >= 0 || b >= 0) {
            int sum = carry;
            if(a >= 0) {
                sum += first.charAt(a--) - '0';
            }
            if (b >= 0) {
                sum += second.charAt(b--) - '0';
            }
            carry = sum >> 1;
            sum = sum & 1;
            sb.append(sum == 0 ? '0' : '1');
        }
        if (carry > 0) {
            sb.append(carry);
        }
        System.out.println(sb.reverse().toString());
    }
}
