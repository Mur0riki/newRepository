package edu.hw2;
sealed interface Expr permits Addition, Constant, Exponent, Multiplication, Negate {
    double evaulate();
}
