package com.example.threads.model;

public class ServerPool {
    private static int count;
    private int totalCapacity;
    private int availableCapacity;

    public ServerPool(int capacity) {
        count++;
        this.totalCapacity = capacity;
        this.availableCapacity = capacity;
    }

    public int allocateCapacity(int requestedCapacity) {
        if (availableCapacity >= requestedCapacity) {
            availableCapacity -= requestedCapacity;
            return requestedCapacity;
        } else {
            return -1;
        }
    }

    public void releaseCapacity(int releasedCapacity) {
        availableCapacity += releasedCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public static int getCount() {
        return count;
    }
}