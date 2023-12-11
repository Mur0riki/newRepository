package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface FibCalculator {
    public long fib(int number);
}

// Реализация кэша
class CacheProxyHandler implements InvocationHandler {
    private final Object target;
    private final Map cache = new HashMap<>();

    CacheProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (method.isAnnotationPresent(Cache.class)) {
            if (cache.containsKey(methodName)) {
                return cache.get(methodName);
            } else {
                long result = (long) method.invoke(target, args);
                cache.put(methodName, result);
                return result;
            }
        }
        return method.invoke(target, args);
    }
}

class CacheProxy {
    public static <T> T create(T obj, Class i) {
        Object proxyInstance = Proxy.newProxyInstance(
            i.getClassLoader(),
            new Class[] {i},
            new CacheProxyHandler(obj)
        );
        @SuppressWarnings("unchecked")
        T proxy = (T) proxyInstance;
        return proxy;
    }
}

@interface Cache {
    boolean persist() default false;
}

class FibCalculatorImpl implements FibCalculator {
    public long fib(int number) {
        if (number <= 1) {
            return number;
        }
        return fib(number - 1) + fib(number - 2);
    }
}

public class Main {
    public static void main(String[] args) {
        FibCalculator fibCalculator = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class);

        long result = proxy.fib(10); // Прокси будет кэшировать результат
    }
}
