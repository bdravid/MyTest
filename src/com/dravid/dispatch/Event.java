package com.dravid.dispatch;

import java.util.Objects;

/**
 * Created by Bhushan on 01/07/2016.
 */
public interface Event {

    public enum EventType{
        NewOrder, AmendOrder,CancelOrder, TradeFill
    }

    public static class EventKey{
        private final EventType eventType;

        public EventKey(EventType eventType) {
            this.eventType = eventType;
        }

        public EventType getEventType(){
            return this.eventType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EventKey)) return false;
            EventKey eventKey = (EventKey) o;
            return Objects.equals(eventType, eventKey.eventType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(eventType);
        }

        @Override
        public String toString() {
            return "EventKey{" +
                    "eventType=" + eventType +
                    '}';
        }
    }

    public interface QueableEvent<K extends EventKey>{
        K getKey();
    }

}
