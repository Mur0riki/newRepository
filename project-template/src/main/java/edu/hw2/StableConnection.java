package edu.hw2;

class StableConnection implements Connection {

    @Override
    public void execute(String command) {
        System.out.println(command);
    }

    public void close() throws Exception {
    }
}
