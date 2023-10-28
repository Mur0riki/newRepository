package edu.hw2;

record Negate(Expr value) implements Expr {

    @Override
    public double evaulate() {
        return (-value.evaulate());
    }
}
