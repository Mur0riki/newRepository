package edu.hw2;

public class Task4 {
    public static void main(String[] args) {
// Пример вызова функции callingInfo
        CallingInfo info = callingInfo();
        System.out.println("Class Name: " + info.getClassName());
        System.out.println("Method Name: " + info.getMethodName());
    }

    public static CallingInfo callingInfo() {
// Получение стека вызовов
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
// Получение информации о вызывающем классе
        String className = stackTraceElements[2].getClassName();
// Получение информации о вызывающем методе
        String methodName = stackTraceElements[2].getMethodName();

// Возвращение экземпляра CallingInfo с полученными данными
        return new CallingInfo(className, methodName);
    }

    record CallingInfo(String className, String methodName) {
        public String getClassName() {
            return className;
        }

        public String getMethodName() {
            return methodName;
        }
    }
}
