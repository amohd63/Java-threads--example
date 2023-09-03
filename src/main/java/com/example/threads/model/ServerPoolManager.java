package com.example.threads.model;

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

    public String allocateServerSpace(int requestedCapacity) {
        lock.lock();
        try {
            for (ServerPool pool : pools) {
                int allocated = pool.allocateCapacity(requestedCapacity);
                if (allocated > 0) {
                    System.out.println(Thread.currentThread().getName() + " " + "Pool-" + ServerPool.getCount() + " " + pool.getAvailableCapacity());
                    return Thread.currentThread().getName() + " " + "Pool-" + ServerPool.getCount();
                }
            }

            // No existing pool had enough space, create a new one
            ServerPool newPool = new ServerPool(1000); // Assuming each pool is 1000 GB
            System.out.println(Thread.currentThread().getName() + " " + "Pool-" + ServerPool.getCount() + " " + newPool.getAvailableCapacity());
            int allocated = newPool.allocateCapacity(requestedCapacity);
            pools.add(newPool);
            return Thread.currentThread().getName() + " " + "Pool-" + ServerPool.getCount();
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