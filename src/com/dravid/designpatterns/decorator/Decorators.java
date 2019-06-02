package com.dravid.designpatterns.decorator;

public class Decorators {

    public static class DashedLineDecorator extends ShapeDecorator {

        private final Shape shape;

        public DashedLineDecorator(Shape shape) {
            super(shape);
            this.shape = shape;
        }

        @Override
        public void draw() {
            System.out.print("Dashed line ");
            shape.draw();
        }
    }

    public static class RedLineDecorator extends ShapeDecorator {

        private final Shape shape;

        public RedLineDecorator(Shape shape) {
            super(shape);
            this.shape = shape;
        }

        @Override
        public void draw() {
            System.out.print("Red line ");
            shape.draw();
        }
    }

}
