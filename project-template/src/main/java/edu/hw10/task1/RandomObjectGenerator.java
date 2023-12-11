package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;

public class RandomObjectGenerator {
    private final Random random;

    public RandomObjectGenerator() {
        random = new Random();
    }

    public <T> T nextObject(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return createObject(constructor);
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethod) throws Exception {
        Method method = clazz.getDeclaredMethod(factoryMethod);
        method.setAccessible(true);
        return (T) method.invoke(null);
    }

    private <T> T createObject(Constructor<T> constructor) throws Exception {
        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            args[i] = createInstance(parameters[i].getType(), parameters[i].getAnnotations());
        }
        return constructor.newInstance(args);
    }

    private Object createInstance(Class<?> clazz, Annotation[] annotations) throws Exception {
        if (clazz.isPrimitive()) {
            return createPrimitiveInstance(clazz);
        }
        if (clazz.isArray()) {
            return createArrayInstance(clazz.getComponentType());
        }
        if (clazz.isEnum()) {
            return createEnumInstance(clazz);
        }
        return createObjectWithAnnotations(clazz, annotations);
    }

    private Object createPrimitiveInstance(Class<?> clazz) {
        if (clazz.equals(boolean.class)) {
            return random.nextBoolean();
        }
        if (clazz.equals(byte.class)) {
            return (byte) random.nextInt();
        }
        if (clazz.equals(short.class)) {
            return (short) random.nextInt();
        }
        if (clazz.equals(int.class)) {
            return random.nextInt();
        }
        if (clazz.equals(long.class)) {
            return random.nextLong();
        }
        if (clazz.equals(float.class)) {
            return random.nextFloat();
        }
        if (clazz.equals(double.class)) {
            return random.nextDouble();
        }
        return null;
    }

    private Object createArrayInstance(Class<?> componentType) throws Exception {
        Object array = Array.newInstance(componentType, random.nextInt(5));
        for (int i = 0; i < Array.getLength(array); i++) {
            Array.set(array, i, createInstance(componentType, new Annotation[0]));
        }
        return array;
    }

    private Object createEnumInstance(Class<?> enumClass) {
        Object[] values = enumClass.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    private <T> T createObjectWithAnnotations(Class<T> clazz, Annotation[] annotations) throws Exception {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(NotNull.class)) {
                return nextObject(clazz);
            }
            if (annotation.annotationType().equals(Min.class)) {
                Min minAnnotation = (Min) annotation;
                int minValue = minAnnotation.value();
                return createBoundedInstance(clazz, minValue, Integer.MAX_VALUE);
            }
            if (annotation.annotationType().equals(Max.class)) {
                Max maxAnnotation = (Max) annotation;
                int maxValue = maxAnnotation.value();
                return createBoundedInstance(clazz, Integer.MIN_VALUE, maxValue);
            }
        }
        return nextObject(clazz);
    }

    private <T> T createBoundedInstance(Class<T> clazz, int minValue, int maxValue) throws Exception {
        if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
            return (T) Integer.valueOf(random.nextInt(maxValue - minValue) + minValue);
        }
        if (clazz.equals(long.class) || clazz.equals(Long.class)) {
            return (T) Long.valueOf(random.nextLong(maxValue - minValue) + minValue);
        }
        if (clazz.equals(float.class) || clazz.equals(Float.class)) {
            return (T) Float.valueOf(random.nextFloat() * (maxValue - minValue) + minValue);
        }
        if (clazz.equals(double.class) || clazz.equals(Double.class)) {
            return (T) Double.valueOf(random.nextDouble() * (maxValue - minValue) + minValue);
        }
        throw new IllegalArgumentException("Unsupported type: " + clazz);
    }
}
