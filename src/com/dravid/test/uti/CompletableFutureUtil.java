package com.dravid.test.uti;

import java.util.concurrent.*;

/**
 * Created by Bdravid on 14/11/2015.
 */
public class CompletableFutureUtil {
    private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

    public static <T> CompletableFuture<T> newTimedCompletableFuture(long time, TimeUnit timeUnit, T defaultValue) {
        CompletableFuture<T> retval = new CompletableFuture<>();
        scheduledExecutor.schedule(() -> {
            System.out.println("Timed out");
            retval.complete(defaultValue);
        }, time, timeUnit);
        return retval;
    }
}
