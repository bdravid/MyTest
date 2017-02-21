package com.dravid.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bhushan on 06/07/2016.
 */
public class Main {
    private static final java.lang.String DELIMITER = " ";
    private static List<Validator> validators; static {
        validators = new ArrayList<>();
        validators.add(new EmptyStringValidator());
        validators.add(new BinaryNumberValidator());
    }
    private final static BinarySum binarySum = new BinarySum(validators);
    private final static OutputPresenter outputPresenter = new ConsoleOutputPresenter();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {
            //System.out.println(String.format("INFO - Received input %s",s));
            String sum = binarySum.sum(parseInput(s));
            outputPresenter.present(sum);
        }
    }

    public interface OutputPresenter{
        void present(String binarySum);
    }

    public static class ConsoleOutputPresenter implements OutputPresenter{
        @Override
        public void present(String binarySum) {
            System.out.println(binarySum);
        }
    }
    public static class BinarySum{
        private final List<Validator> validators;

        public BinarySum(List<Validator> validators) {
            this.validators = validators;
        }

        public String sum(List<String> binaryNumbersAsString) {
            sanityCheck(binaryNumbersAsString);
            if (binaryNumbersAsString.size() == 2) { //TODO : expand the program for more numbers
                return binarySum(binaryNumbersAsString.get(0), binaryNumbersAsString.get(1));
            } else {
                throw new UnsupportedOperationException("Currently only two numbers addition supported");
            }
        }

        public String binarySum(String a, String b) {
            if (a == null || b == null) return "";
            int first = a.length() - 1;
            int second = b.length() - 1;
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            while (first >= 0 || second >= 0) {
                int sum = carry;
                if (first >= 0) {
                    sum += a.charAt(first) - '0';
                    first--;
                }
                if (second >= 0) {
                    sum += b.charAt(second) - '0';
                    second--;
                }
                carry = sum >> 1;
                sum = sum & 1;
                sb.append(sum == 0 ? '0' : '1');
            }
            if (carry > 0)
                sb.append('1');

            sb.reverse();
            return String.valueOf(sb);
        }


        private void sanityCheck(List<String> binaryNumbersAsString) {
            if (binaryNumbersAsString == null || binaryNumbersAsString.isEmpty()) {
                throw new IllegalArgumentException("Null or empty binary list");
            }
            for (Validator validator : validators) {
                for(String binaryNumberAsString : binaryNumbersAsString) {
                    if(!validator.isValid(binaryNumberAsString)) {
                        throw new IllegalArgumentException("Validator failed -"+validator.id()+", invalid binary number passed " + binaryNumberAsString);
                    }
                }
            }
            //System.out.println("INFO : All validations passed");
        }
    }

    public interface Validator{
        String id();
        boolean isValid(String binaryNumberAsString);
    }

    public static class EmptyStringValidator implements Validator {

        @Override
        public String id() {
            return "EmptyStringValidator";
        }

        @Override
        public boolean isValid(String binaryNumberAsString) {
            if(binaryNumberAsString==null || binaryNumberAsString.isEmpty()){
                return false;
            }
            return true;
        }
    }

    public static class BinaryNumberValidator implements Validator{

        @Override
        public String id() {
            return "BinaryNumberValidator";
        }

        @Override
        public boolean isValid(String binaryNumberAsString) {
            for (int i = 0; i < binaryNumberAsString.length(); i++) {
                Character charAt = binaryNumberAsString.charAt(i);
                if (!(charAt.equals('0') || charAt.equals('1'))) {
                    return false;
                }
            }
            return true;
        }
    }
    private static List<String> parseInput(String input) {
        if(input==null || input.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }
        List<String> parsed = Arrays.asList(input.split(DELIMITER));
        //System.out.println("INFO After parsing numbers "+parsed);
        return parsed;
    }
}
