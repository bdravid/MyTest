package com.dravid.threading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPongLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        boolean[] pingPong = new boolean[]{true};
        Thread t1 = new PingThread(lock, condition, pingPong);
        Thread t2 = new PongThread(lock, condition, pingPong);
        t1.start();
        t2.start();

        Thread.sleep(10000);
    }

    private static class PingThread extends Thread {
        private final Condition condition;
        private final Lock lock;
        private boolean[] pingPong;

        public PingThread(Lock lock, Condition condition, boolean[] pingPong) {
            this.lock = lock;
            this.condition = condition;
            this.pingPong = pingPong;
        }

        public void run(){
            while(true) {
                try {
                    lock.lock();
                    if(!pingPong[0]){
                        condition.await();
                    }
                    System.out.println("Ping");
                    pingPong[0] = !pingPong[0];
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    condition.signalAll();
                    lock.unlock();
                }
            }
        }
    }

    private static class PongThread extends Thread {
        private final Condition condition;
        private final Lock lock;
        private final boolean[] pingPong;

        public PongThread(Lock lock, Condition condition, boolean[] pingPong) {
            this.lock = lock;
            this.condition = condition;
            this.pingPong = pingPong;
        }

        public void run(){
            while(true) {
                try {
                    lock.lock();
                    if (pingPong[0]) {
                        condition.await();
                    }
                    System.out.println("Pong");
                    pingPong[0] = !pingPong[0];
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    condition.signalAll();
                    lock.unlock();
                }
            }
        }
    }
}
