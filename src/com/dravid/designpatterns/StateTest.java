package com.dravid.designpatterns;

/**
 * Created by Bdravid on 21/02/2017.
 */
public class StateTest {

    public static void main(String[] args) {
        FanContext context = new FanContext();
        for (int i = 0; i < 5; i++) {
            context.action();
        }
    }

    private static class FanContext {
        private FanState fanState;

        public FanContext() {
            this.fanState = new Off();
        }

        void setFanState(FanState state) {
            this.fanState = state;
        }

        public void action() {
            fanState.action(this);
        }
    }

    private interface FanState {
        void action(FanContext context);
    }

    private static class Off implements FanState {
        @Override
        public void action(FanContext context) {
            context.setFanState(new Low());
            System.out.println(" Off ");
        }
    }

    private static class Low implements FanState {

        @Override
        public void action(FanContext context) {
            context.setFanState(new Fast());
            System.out.println(" Low ");
        }
    }

    private static class Fast implements FanState {

        @Override
        public void action(FanContext context) {
            context.setFanState(new Off());
            System.out.println(" Fast ");
        }
    }
}
