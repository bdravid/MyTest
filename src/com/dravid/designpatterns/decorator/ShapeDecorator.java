package com.dravid.designpatterns.decorator;

public abstract class ShapeDecorator implements Shape{
    private final Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }
}
