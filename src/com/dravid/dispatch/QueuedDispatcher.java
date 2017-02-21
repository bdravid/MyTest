package com.dravid.dispatch;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by Bhushan on 01/07/2016.
 */
public class QueuedDispatcher<K extends Event.QueableEvent> {
    private final ExecutorService executorService;
    private final Map<Event.EventType, EventHandler> eventHandlers;

    private final Map<Event.EventKey, BlockingQueue<K>> queueMap = new ConcurrentHashMap<>();
    private final Map<Event.EventKey, Runnable> runnables = new ConcurrentHashMap<>();

    public QueuedDispatcher(ExecutorService executorService, Map<Event.EventType, EventHandler> eventHandlers) {
        this.executorService = executorService;
        this.eventHandlers = eventHandlers;
    }

    public QueuedDispatcher(Map<Event.EventType, EventHandler> eventHandlers) {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), r -> new Thread(r, "queued-dispatcher-")), eventHandlers);
    }

    public void dispatch(K event) {
        if (!queueMap.containsKey(event.getKey())) {
            System.out.println("Registering new queue for event type - " + event.getKey());
            queueMap.putIfAbsent(event.getKey(), new LinkedBlockingQueue<>());
        }
        BlockingQueue<K> queue = queueMap.get(event.getKey());
        queue.add(event); //add because we are using unbounded queue
        if (!runnables.containsKey(event.getKey())) {
            System.out.println("Initiating worker process for " + event.getKey());
            runnables.putIfAbsent(event.getKey(), newWorker(queue));
        }
        executorService.submit(runnables.get(event.getKey()));
    }

    public void stop() {
        System.out.println("Event types size = " + this.queueMap.size());
        System.out.println("------------------------------------------");
        queueMap.keySet().forEach(k -> System.out.println("Event type = " + k + ", processed events = "+queueMap.get(k).size()));
        System.out.println("------------------------------------------");
        System.out.println("Runnables size = " + runnables.size());
        executorService.shutdownNow();
    }

    private Runnable newWorker(BlockingQueue<K> queue) {
        return new Runnable() {
            public void run() {
                while (!Thread.interrupted()) {
                    sout("Waiting for event...");
                    K event = null;
                    try {
                        event = queue.take();
                    } catch (InterruptedException e) {
                        sout("Received shutdown signal, exiting...");
                        break;
                    }
                    sout("Got event to process " + event);
                    EventHandler eventHandler = eventHandlers.get(event.getKey().getEventType());
                    sout("Found event handler for " + event);
                    eventHandler.handle(event);
                }
            }

            private void sout(String str){
                System.out.println(Thread.currentThread().getName()+" "+str);
            }
        };
    }
}
