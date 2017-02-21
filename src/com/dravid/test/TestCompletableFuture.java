package com.dravid.test;

import com.dravid.test.uti.CompletableFutureUtil;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Created by Bdravid on 14/11/2015.
 */
public class TestCompletableFuture {
    private static CompletableFuture<Double> timeOut = CompletableFutureUtil.newTimedCompletableFuture(3, TimeUnit.SECONDS, 0d);
    private static LinkedBlockingQueue<Double> q = new LinkedBlockingQueue<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(5);
    public static void main(String args[]) {
        first();
    }

    private static void second() {
        CompletableFuture<Double> f1 = CompletableFuture.supplyAsync(TestCompletableFuture::take);
        Double d = null;
        try {
            d = f1.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("Done " + d);
    }

    private static void first() {
        CompletableFuture<Double> f1 = CompletableFuture.supplyAsync(TestCompletableFuture::take, pool);
        CompletableFuture<Double> f2 = CompletableFuture.supplyAsync(TestCompletableFuture::take, pool);
        CompletableFuture<Double> fd1 = f1.applyToEitherAsync(timeOut, Function.identity(), pool).thenApplyAsync((r) -> {
            f1.cancel(true);
            return 0d;
        }, pool);
        CompletableFuture<Double> fd2 = f2.applyToEitherAsync(timeOut, Function.identity(), pool).thenApplyAsync((r) -> {
            f2.cancel(true);
            return 0d;
        }, pool);
        CompletableFuture<Void> all = CompletableFuture.allOf(fd1,fd2);
        System.out.println("Joining");
        all.join();
    }

    private static Double take(){
        try {
            return q.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0d;
    }
}
