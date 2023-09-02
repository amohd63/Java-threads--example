public class CloudService {
    private static CloudService cloudService = null;
    private ServerPoolManager poolManager;

    private CloudService() {
        this.poolManager = new ServerPoolManager();
    }

    public static synchronized CloudService getInstance() {
        if (cloudService == null) {
            cloudService = new CloudService();
        }

        return cloudService;
    }

    public int requestServerSpace(int requestedCapacity, String user) {
        return poolManager.allocateServerSpace(requestedCapacity, user);
    }

    public void releaseServerSpace(int releasedCapacity) {
        poolManager.releaseServerSpace(releasedCapacity);
    }
}
