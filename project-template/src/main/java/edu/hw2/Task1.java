package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaulate();

        public record Constant(double value) implements Expr {
            @Override
            public double evaulate() {
                double result = this.value;
                return result;
            }
        }

        public record Negate(Constant value) implements Expr  {

            @Override
            public double evaulate() {
                return (-1*value.evaulate());
            }
        }

        public record Exponent(Constant value, double exponent) implements Expr{
            @Override
            public double evaulate(){
                return Math.pow(value.evaulate(),exponent);
            }
        }
        public record Addition(Constant firstValue, Constant secondValue) implements Expr{

            @Override
            public double evaulate() {
                return firstValue.evaulate()+secondValue.evaulate();
            }
        }
        public record Multiplication(Constant firstValue, Constant secondValue) implements Expr {
            @Override
            public double evaulate() {
                return firstValue.evaulate()*secondValue.evaulate();
            }
        }
    }
}
