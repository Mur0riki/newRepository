package edu.hw2;

public class Task2 {

    public static class Rectangle {
        private final int width;
        private final int height;

        public Rectangle(int width, int height){
            this.width = width;
            this.height = height;
        }

        public Rectangle() {
            this.width = 0;
            this.height = 0;
        }

        public double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        private final int width = 0;
        private final int height = 0;
        private final int size;

        Square(int size){
            this.size = size;
        }

        @Override public double area() {
            return size*size;
        }

    }
}
