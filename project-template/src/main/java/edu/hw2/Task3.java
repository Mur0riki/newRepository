package edu.hw2;

public class Task3 {
    public interface Connection extends AutoCloseable {
        void execute(String command);

        void close() throws Exception;
    }

    public class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            System.out.println(command);
        }

        public void close() throws Exception {
        }
    }

    public class FaultyConnection implements Connection {

        @Override
        public void execute(String command) {
            throw new ConnectionException();
        }

        public void close() throws Exception {
            throw new ConnectionException();
        }
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public class DefaultConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection() {

            if (Math.random() % 2 == 0) {
                return new FaultyConnection();
            }
            return new StableConnection();
        }
    }

    public class FaultyConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public class ConnectionException extends RuntimeException {
    }

    public final class PopularCommandExecutor {
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
            boolean executed = false;
            try (Connection connection = this.manager.getConnection();) {
                for (int j = 0; j < maxAttempts && !executed; j++) {
                    try {
                        connection.execute(command);
                        executed = true;
                        break;
                    } catch (ConnectionException e) {
                        exception = e;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!executed) {
                throw exception;
            }
        }
    }
}

