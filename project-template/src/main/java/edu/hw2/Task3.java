package edu.hw2;

public class Task3 {
    interface Connection extends AutoCloseable {
        void execute(String command);

        void close() throws Exception;
    }

    class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            System.out.println(command);
        }

        public void close() throws Exception {
        }
    }

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

    interface ConnectionManager {
        Connection getConnection();
    }

    class DefaultConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection() {

            if (Math.random() % 2 == 0) {
                return new FaultyConnection();
            }
            return new StableConnection();
        }
    }

    class FaultyConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    class ConnectionException extends RuntimeException {

        public ConnectionException() {
            super();
        }

        public ConnectionException(String message) {
            super(message);
        }

        public ConnectionException(Throwable cause) {
            super(cause);
        }

        public ConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
            this.manager = manager;
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) {
            ConnectionException exception = null;
            try (Connection connection = this.manager.getConnection();) {
                for (int j = 0; j < maxAttempts; j++) {
                    try {
                        connection.execute(command);
                        break;
                    } catch (ConnectionException e) {
                        if (exception == null) {
                            exception = e;
                        } else {
                            exception.addSuppressed(e);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (exception != null) {
                throw exception;
            }
        }
    }
}

