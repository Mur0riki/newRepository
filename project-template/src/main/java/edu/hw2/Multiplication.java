package edu.hw2;

record Multiplication(Expr firstValue, Expr secondValue) implements Expr {
    @Override
    public double evaulate() {
        return firstValue.evaulate() * secondValue.evaulate();
    }
}
