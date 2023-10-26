package edu.hw2;

public class Task1 {
}

sealed interface Expr {
    double evaulate();
}

record Constant(double value) implements Expr {
    @Override
    public double evaulate() {
        return value;
    }
}

record Negate(Expr value) implements Expr {

    @Override
    public double evaulate() {
        return (-value.evaulate());
    }
}

record Exponent(Expr value, int exponent) implements Expr {
    @Override
    public double evaulate() {
        return Math.pow(value.evaulate(), exponent);
    }
}

record Addition(Expr firstValue, Expr secondValue) implements Expr {

    @Override
    public double evaulate() {
        return firstValue.evaulate() + secondValue.evaulate();
    }
}

record Multiplication(Expr firstValue, Expr secondValue) implements Expr {
    @Override
    public double evaulate() {
        return firstValue.evaulate() * secondValue.evaulate();
    }
}
