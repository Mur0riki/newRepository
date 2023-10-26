package edu.hw2;

public class Task2 {
}
 class Rectangle {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        this.width = 0;
        this.height = 0;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(height, width);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(height, width);
    }

    public double area() {
        return width * height;
    }
}

 class Square extends Rectangle {
    private final int width = 0;
    private final int height = 0;
    private final int size;

    Square(int size) {
        this.size = size;
    }

    @Override public double area() {
        return size * size;
    }

}
