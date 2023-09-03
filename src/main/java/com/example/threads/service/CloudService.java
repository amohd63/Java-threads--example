package com.example.threads.service;

import com.example.threads.model.Cloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudService {
    private Cloud cloud;

    public CloudService() {
        this.cloud = Cloud.getInstance();
    }

    public String requestServer(int size) {
        return cloud.requestServerSpace(size);
    }
}
