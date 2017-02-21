package com.dravid.threading;

/**
 * Created by Bhushan on 02/07/2016.
 */
public class ThreadLocalTest {
    public static class IntProvider implements Runnable {
        private static ThreadLocal<Integer> state = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };
        private final boolean even;
        private boolean interrupted = false;

        public IntProvider(boolean even) {
            this.even = even;
        }

        @Override
        public void run() {
            while (!interrupted) {
                if(even) {
                    if (state.get() == 0) {
                        state.set(2);
                    } else {
                        state.set(state.get() + 2);
                    }
                } else {
                    if (state.get() == 0) {
                        state.set(1);
                    }else{
                        state.set(state.get()+2);
                    }
                }
                System.out.println((even?"even":"odd")+"-"+state.get());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted...");
                    this.interrupted = true;
                    System.out.println("Interrupted status = " + interrupted);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new IntProvider(true));
        Thread t2 = new Thread(new IntProvider(false));
        t1.start();
        t2.start();
        Thread.sleep(5000);
        t1.interrupt();
        t2.interrupt();
    }
}
