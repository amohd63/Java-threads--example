import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

public class ServerPoolManager {
    private List<ServerPool> pools;
    private Lock lock;

    public ServerPoolManager() {
        this.pools = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public int allocateServerSpace(int requestedCapacity, String user) {
        lock.lock();
        try {
            for (ServerPool pool : pools) {
                int allocated = pool.allocateCapacity(requestedCapacity);
                if (allocated > 0) {
                    System.out.println("Pool-" + ServerPool.getCount() + " " + user + " " + allocated);
                    return allocated;
                }
            }

            // No existing pool had enough space, create a new one
            ServerPool newPool = new ServerPool(1000); // Assuming each pool is 1000 GB
            int allocated = newPool.allocateCapacity(requestedCapacity);
            System.out.println("Pool-" + ServerPool.getCount() + " " + user + " " + allocated);
            pools.add(newPool);
            return allocated;
        } finally {
            lock.unlock();
        }
    }
    public void releaseServerSpace(int releasedCapacity) {
        lock.lock();
        try {
            for (ServerPool pool : pools) {
                if (pool.allocateCapacity(releasedCapacity) > 0) {
                    return;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}