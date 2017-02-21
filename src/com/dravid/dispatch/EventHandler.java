package com.dravid.dispatch;

/**
 * Created by Bhushan on 01/07/2016.
 */
public interface EventHandler {
    Event.EventType getEventType();
    void handle(Event.QueableEvent event);

    public static class GenericEventHandler implements EventHandler{
        private final Event.EventType eventType;

        public GenericEventHandler(Event.EventType eventType) {
            this.eventType = eventType;
        }

        @Override
        public Event.EventType getEventType() {
            return eventType;
        }

        @Override
        public void handle(Event.QueableEvent event) {
            System.out.println("Handling event - " + event);
        }
    }
}
