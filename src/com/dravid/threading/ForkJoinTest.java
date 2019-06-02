package com.dravid.threading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinTest {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool fjp = new ForkJoinPool();
        ToUpper toUpper = new ToUpper("HelloWorld", fjp);
        fjp.submit(toUpper);
        fjp.awaitTermination(3, TimeUnit.SECONDS);
    }

    private static class ToUpper extends RecursiveAction {
        private final String string;
        private final int threshold = 3;
        private final ForkJoinPool fjp;

        public ToUpper(String string, ForkJoinPool fjp) {
            this.string = string;
            this.fjp = fjp;
        }

        @Override
        protected void compute() {
            int length = string.length();
            if (length <= threshold) {
                System.out.println(string.toUpperCase());
            } else {
                fjp.submit(new ToUpper(string.substring(0, length / 2), fjp));
                fjp.submit(new ToUpper(string.substring((length / 2) + 1, length), fjp));
            }
        }
    }
}
