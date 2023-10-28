package edu.hw2;

record Constant(double value) implements Expr {
    @Override
    public double evaulate() {
        return value;
    }
}
