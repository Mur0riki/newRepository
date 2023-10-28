package edu.hw2;

interface Connection extends AutoCloseable {
    void execute(String command);

    void close() throws Exception;
}
