package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyExample {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        // Создаем новый класс с помощью библиотеки ByteBuddy
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString")) // Выбираем метод toString
            .intercept(FixedValue.value("Hello, ByteBuddy!")) // Устанавливаем тело метода
            .make()
            .load(ByteBuddyExample.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        // Создаем экземпляр класса
        Object instance = dynamicType.newInstance();

        // Вызываем метод toString и выводим результат
        System.out.println(instance.toString());
    }
}

class HelloByteBuddy {
    @Override
    public String toString() {
        return "Default toString method";
    }
}
