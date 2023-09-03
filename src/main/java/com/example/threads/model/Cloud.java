package com.example.threads.model;

public class Cloud {
    private static Cloud cloudService = null;
    private ServerPoolManager poolManager;

    private Cloud() {
        this.poolManager = new ServerPoolManager();
    }

    public static synchronized Cloud getInstance() {
        if (cloudService == null) {
            cloudService = new Cloud();
        }

        return cloudService;
    }

    public String requestServerSpace(int requestedCapacity) {
        return poolManager.allocateServerSpace(requestedCapacity);
    }

    public void releaseServerSpace(int releasedCapacity) {
        poolManager.releaseServerSpace(releasedCapacity);
    }
}
