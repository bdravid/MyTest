package com.dravid.designpatterns.decorator;

import com.dravid.designpatterns.decorator.Decorators.DashedLineDecorator;
import com.dravid.designpatterns.decorator.Decorators.RedLineDecorator;
import com.dravid.designpatterns.decorator.Shapes.Circle;
import com.dravid.designpatterns.decorator.Shapes.Square;

public class DecoratorTest {
    public static void main(String[] args) {
        Shape circle = new DashedLineDecorator(new Circle());
        circle.draw();
        Circle threeDCircle = new Shapes.ThreeDCircle();
        System.out.println();
        Shape square = new DashedLineDecorator(new RedLineDecorator(new Square()));
        square.draw();

        System.out.println();
        System.out.println();
        draw(threeDCircle);
        draw(circle);

    }

    private static void draw(Shape shape) {
        System.out.println("Shape...");
    }

    private static void draw(Circle shape) {
        System.out.println("Circle...");
    }

    private static void draw(Shapes.ThreeDCircle shape) {
        System.out.println("3D Circle...");
    }
}
