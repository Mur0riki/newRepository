package edu.project5;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark) // объявляет класс-контейнер, который будет хранить состояние теста и его зависимости
public class MethodAccessBenchmark {

    private static Student student = new Student("Jhon");
    private Method directMethod;
    private Method reflectMethod;
    private MethodHandle getFieldValue;

    private MethodHandle getter;

    MethodHandle site;
    private static final MethodType methodType = MethodType.methodType(String.class);

    @Setup(Level.Iteration) // выполняется один раз перед каждой итерацией измерения производительности
    public void setup() throws Throwable {
        student = new Student("John");
        try {
            directMethod = Student.class.getMethod("getName");
            reflectMethod = Student.class.getDeclaredMethod("getName");
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            getFieldValue = lookup.findVirtual(Student.class,"getName", MethodType.methodType(String.class));
            reflectMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Benchmark // обозначает метод-тест, который измеряет производительность
    public String directAccess() {
        return student.getName();
    }

    @Benchmark
    public String reflectAccess() throws IllegalAccessException, InvocationTargetException {
        return (String) reflectMethod.invoke(student);
    }
    @Benchmark
    public String methodHandlesAccess() throws Throwable {
        return (String) getFieldValue.invoke(student);
    }

    /*public String lambdaMetafactoryAccess() throws Throwable {
        return (String) getter.invokeExact();
    }*/

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(MethodAccessBenchmark.class.getSimpleName())
            .forks(1)
            .warmupIterations(5)
            .measurementIterations(5)
            .mode(Mode.Throughput)
            .build();
        new Runner(opt).run();
    }
    interface Getter {
        String get(Student student);
    }
}
