package edu.hw2;

record Exponent(Expr value, int exponent) implements Expr {
    @Override
    public double evaulate() {
        return Math.pow(value.evaulate(), exponent);
    }
}
