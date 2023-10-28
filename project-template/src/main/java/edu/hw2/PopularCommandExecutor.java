package edu.hw2;

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
