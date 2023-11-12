package edu.hw2;

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
