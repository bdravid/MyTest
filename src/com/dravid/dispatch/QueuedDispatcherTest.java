package com.dravid.dispatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Bhushan on 01/07/2016.
 */
public class QueuedDispatcherTest {
    public static void main(String args[]) throws InterruptedException {
        Map<Event.EventType, EventHandler> eventHandlers = new HashMap<>();
        eventHandlers.put(Event.EventType.NewOrder, new EventHandler.GenericEventHandler(Event.EventType.NewOrder));
        eventHandlers.put(Event.EventType.AmendOrder, new EventHandler.GenericEventHandler(Event.EventType.AmendOrder));
        eventHandlers.put(Event.EventType.CancelOrder, new EventHandler.GenericEventHandler(Event.EventType.CancelOrder));
        eventHandlers.put(Event.EventType.TradeFill, new EventHandler.GenericEventHandler(Event.EventType.TradeFill));

        QueuedDispatcher<Event.QueableEvent> queuedDispatcher = new QueuedDispatcher<>(eventHandlers);
        queuedDispatcher.dispatch(new NewOrderRequest("O-1"));
        queuedDispatcher.dispatch(new NewOrderRequest("O-1"));
        queuedDispatcher.dispatch(new NewOrderRequest("O-1"));
        queuedDispatcher.dispatch(new NewOrderRequest("O-1"));
        Thread.sleep(500);
        queuedDispatcher.stop();
    }

    public static class NewOrderRequest implements Event.QueableEvent<Event.EventKey> {
        private final Event.EventKey eventKey ;
        private final String orderId;

        public NewOrderRequest(String orderId) {
            this.orderId = orderId;
            this.eventKey = new OrderEventKey(orderId);
        }

        @Override
        public Event.EventKey getKey() {
            return this.eventKey;
        }

        public String getOrderId() {
            return orderId;
        }

        @Override
        public String toString() {
            return "NewOrderRequest{" +
                    "eventKey=" + eventKey +
                    ", orderId='" + orderId + '\'' +
                    '}';
        }
    }
    public static class OrderEventKey extends Event.EventKey {
        private final String orderId;
        public OrderEventKey(String orderId) {
            super(Event.EventType.NewOrder);
            this.orderId = orderId;
        }

        public String getOrderId() {
            return orderId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OrderEventKey)) return false;
            if (!super.equals(o)) return false;
            OrderEventKey that = (OrderEventKey) o;
            return Objects.equals(orderId, that.orderId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), orderId);
        }

        @Override
        public String toString() {
            return "OrderEventKey{" +
                    "orderId='" + orderId + '\'' +
                    "} " + super.toString();
        }
    }
}
