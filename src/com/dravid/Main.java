package com.dravid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bhushan on 06/07/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {
            String[] splits = s.split(" ");
            Rectangle a = new Rectangle(splits[0], splits[1], splits[2], splits[3]);
            Rectangle b = new Rectangle(splits[4], splits[5], splits[6], splits[7]);
            System.out.println(overlap(a, b));
        }
    }

    private static boolean overlap(Rectangle a, Rectangle b) {
        List<String> coordinatesOfA = a.coordinatesAsListOfString();
        List<String> coordinatesOfB = b.coordinatesAsListOfString();
        for (String coordinateA : coordinatesOfA) {
            if(coordinatesOfB.contains(coordinateA)){
                return true;
            }
        }
        return false;
    }

    public static class Rectangle{
        private final int x,y;
        private final int width,height;
        private final Coordinates lowerLeft;
        private final Coordinates lowerRight;
        private final Coordinates upperLeft;
        private final Coordinates upperRight;

        public Rectangle(String x, String y, String width, String height) {
            this(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(width), Integer.parseInt(height));
        }
        public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            if (width <= 0) {
                throw new IllegalArgumentException("Width of a rectangle should be greater than 0, found - " + width);
            }
            if (height <= 0) {
                throw new IllegalArgumentException("Height of a rectangle should be greater than 0, found - " + height);
            }
            this.width = width;
            this.height = height;
            lowerLeft = new Coordinates(x, y);
            lowerRight = new Coordinates(x + width, y);
            upperLeft = new Coordinates(x, y + height);
            upperRight = new Coordinates(x + width, y + height);
        }

        public Coordinates getLowerLeft() {
            return lowerLeft;
        }

        public Coordinates getLowerRight() {
            return lowerRight;
        }

        public Coordinates getUpperLeft() {
            return upperLeft;
        }

        public Coordinates getUpperRight() {
            return upperRight;
        }

        public List<String> coordinatesAsListOfString(){
            return Arrays.asList(lowerLeft.toString(), lowerRight.toString(), upperLeft.toString(), upperRight.toString());
        }
    }
    public static class Coordinates{
        private final int x,y;
        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return String.format("%s,%s",x,y);
        }
    }
}
