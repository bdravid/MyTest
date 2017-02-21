package com.dravid.test;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Short.parseShort;
import static java.lang.String.valueOf;

/**
 * Created by Bdravid on 12/02/2017.
 */
public class BinarySum {

    public static void main(String[] args) {
        String first = "0", second = "111";
        String sum = sum(first, second);
        System.out.println(first + " + " + second + " = " + sum);
        System.out.println(parseInt(first, 2) + " + " + parseInt(second, 2) + " = " + parseInt(sum, 2));
    }

    private static String sum(String first, String second) {
        String first1 = reverse(first);
        String second1 = reverse(second);
        StringBuilder sum = new StringBuilder();
        short carryOver = 0;
        for (int i = 0; i < max(first1.length(), second1.length()); i++) {
            Result result = sum(atIndex(first1, i), atIndex(second1, i));
            Result carrySum = sum(result.sum, carryOver);
            sum.append(carrySum.sum);
            carryOver = (short) max(result.carryOver, carrySum.carryOver);
        }
        if (carryOver == 1) {
            sum.append(1);
        }
        return sum.reverse().toString();
    }

    private static short atIndex(String str, int i) {
        if (i < str.length()) {
            return parseShort(valueOf(str.charAt(i)));
        }
        return 0;
    }

    private static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    private static Result sum(short first, short second) {
        short sum = (short) (first + second);
        if (sum == 2) {
            return new Result(0, 1);
        } else if (sum == 0) {
            return new Result();
        } else {
            return new Result(1, 0);
        }
    }

    private static class Result{
        private short sum;
        private short carryOver;

        public Result(int sum, int carryOver) {
            this.sum = (short) sum;
            this.carryOver = (short) carryOver;
        }

        public Result() {
            this.sum = 0;
            this.carryOver = 0;
        }
    }

}
