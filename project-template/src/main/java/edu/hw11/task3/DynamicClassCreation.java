package edu.hw11.task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.JavaModule;
import org.objectweb.asm.Opcodes;

public class DynamicClassCreation {

   public static void main(String[] args) {
// Создаем новый класс с использованием библиотеки ByteBuddy
        DynamicType.Unloaded dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciClass")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "n")
            .intercept(new CustomByteCodeAppender())
            .make();

// Загружаем динамически созданный класс
        Class dynamicTypeLoaded = dynamicType.load(DynamicClassCreation.class.getClassLoader(),
            (ClassLoadingStrategy) JavaModule.class.getClassLoader()
        ).getLoaded();

// Создаем экземпляр класса
        Object instance;
        try {
// Вызываем метод fib с параметром 10
            instance = dynamicTypeLoaded.newInstance();
            long result = (long) dynamicTypeLoaded.getMethod("fib", int.class).invoke(instance, 10);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CustomByteCodeAppender implements ByteCodeAppender, Implementation {

        @Override
        public Size apply(MethodVisitor mv, Implementation.Context ctx, MethodDescription instrumentedMethod) {
            mv.visitCode();
            Label elseLabel = new Label();
            Label endLabel = new Label();
            mv.visitVarInsn(Opcodes.ILOAD, 1); // Помещаем значение аргумента n в стек
            mv.visitJumpInsn(Opcodes.IFLE, elseLabel); // Если n <= 0, переходим к elseLabel
            mv.visitVarInsn(Opcodes.ILOAD, 1); // Помещаем значение аргумента n в стек
            mv.visitInsn(Opcodes.ICONST_1); // Загружаем константу 1 в стек
            mv.visitJumpInsn(Opcodes.IF_ICMPLT, elseLabel); // Если n < 2, переходим к elseLabel
            mv.visitVarInsn(Opcodes.ILOAD, 1); // Помещаем значение аргумента n в стек
            mv.visitInsn(Opcodes.ICONST_1); // Загружаем константу 1 в стек
            mv.visitInsn(Opcodes.ISUB); // Вычитаем 1 из n
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciClass", "fib", "(I)J", false); // Вызываем рекурсивно метод fib(n-1)
            mv.visitVarInsn(Opcodes.ILOAD, 1); // Помещаем значение аргумента n в стек
            mv.visitInsn(Opcodes.ICONST_2); // Загружаем константу 2 в стек
            mv.visitInsn(Opcodes.ISUB); // Вычитаем 2 из n
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciClass", "fib", "(I)J", false); // Вызываем рекурсивно метод fib(n-2)
            mv.visitInsn(Opcodes.LADD); // Складываем результаты
            mv.visitJumpInsn(Opcodes.GOTO, endLabel); // Переходим к endLabel

            mv.visitLabel(elseLabel);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitInsn(Opcodes.I2L); // Конвертируем целочисленное значение n в тип long
            mv.visitLabel(endLabel);
            mv.visitInsn(Opcodes.LRETURN); // Возвращаем результат типа long

            mv.visitMaxs(0, 0);
            mv.visitEnd();

            return new Size(2, instrumentedMethod.getStackSize());
        }

        @Override
        public ByteCodeAppender appender(Target target) {
            return null;
        }

        @Override
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return null;
        }
    }
}

