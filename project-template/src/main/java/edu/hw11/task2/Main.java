package edu.hw11.task2;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        SumInterceptor sumInterceptor = new SumInterceptor();
        int result = new ByteBuddy()
            .subclass(Sample.class)
            .method(named("calculate")
                .and(isDeclaredBy(Sample.class)
                .and(returns(int.class))))
            .intercept(MethodDelegation.to(SumInterceptor.class))
            .make()
            .load(Main.class.getClassLoader())
            .getLoaded()
            .newInstance()
            .calculate(10,10);
        System.out.println(result);
    }
}
