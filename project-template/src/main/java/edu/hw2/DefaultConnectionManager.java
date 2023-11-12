package edu.hw2;

class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {

        if (Math.random() % 2 == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
