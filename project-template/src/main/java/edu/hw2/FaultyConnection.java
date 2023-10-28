package edu.hw2;

class FaultyConnection implements Connection {

    @Override
    public void execute(String command) {
        if (Math.random() % 2 == 0) {
            throw new ConnectionException();
        }
    }

    public void close() throws Exception {
        throw new ConnectionException();
    }
}
