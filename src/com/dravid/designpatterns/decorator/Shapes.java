package com.dravid.designpatterns.decorator;

public class Shapes {
    public static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.print("Circle ");
        }
    }

    public static class ThreeDCircle extends Circle{
        public void draw(){
            System.out.println("3D Circle");
        }
    }

    public static class Square implements Shape {

        @Override
        public void draw() {
            System.out.print("Square ");
        }
    }
}
