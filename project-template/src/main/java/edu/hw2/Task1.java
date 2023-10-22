package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaulate();

        public record Constant(double value) implements Expr {
            @Override
            public double evaulate() {
                return value;
            }
        }

        public record Negate(Expr value) implements Expr {

            @Override
            public double evaulate() {
                return (-1 * value.evaulate());
            }
        }

        public record Exponent(Expr value, int exponent) implements Expr {
            @Override
            public double evaulate() {
                return Math.pow(value.evaulate(), exponent);
            }
        }

        public record Addition(Expr firstValue, Expr secondValue) implements Expr {

            @Override
            public double evaulate() {
                return firstValue.evaulate() + secondValue.evaulate();
            }
        }

        public record Multiplication(Expr firstValue, Expr secondValue) implements Expr {
            @Override
            public double evaulate() {
                return firstValue.evaulate() * secondValue.evaulate();
            }
        }
    }
}
