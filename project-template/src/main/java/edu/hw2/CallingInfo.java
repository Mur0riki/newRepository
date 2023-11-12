package edu.hw2;

record CallingInfo(String className, String methodName) {
    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}
