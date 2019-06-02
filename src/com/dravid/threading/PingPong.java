package com.dravid.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PingPong {
    public static void main(String[] args) throws InterruptedException {
        StringBuilder sb = new StringBuilder("ping");

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(new PingRunnable(sb));
        threadPool.submit(new PongRunnable(sb));
        threadPool.awaitTermination(1, TimeUnit.MINUTES);
    }

    private static class PingRunnable implements Runnable {

        private final StringBuilder sb;

        public PingRunnable(StringBuilder sb) {
            this.sb = sb;
        }

        @Override
        public void run() {
            synchronized (sb) {
                while (true) {
                    if(sb.toString().equals("ping")) {
                        System.out.println("Ping");
                        sb.delete(0, sb.length());
                        sb.append("pong");
                        sb.notifyAll();
                    } else {
                        try {
                            sb.wait();
                        } catch (InterruptedException e) {
                            //nothing
                        }
                    }
                }
            }
        }
    }

    private static class PongRunnable implements Runnable {
        private final StringBuilder sb;

        public PongRunnable(StringBuilder sb) {
            this.sb = sb;
        }

        @Override
        public void run() {
            synchronized (sb) {
                while (true) {
                    if (sb.toString().equals("pong")) {
                        System.out.println("Pong");
                        sb.delete(0, sb.length());
                        sb.append("ping");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            //nothing
                        }
                        sb.notifyAll();
                    } else {
                        try {
                            sb.wait();
                        } catch (InterruptedException e) {
                            //nothing
                        }
                    }
                }
            }
        }
    }
}
