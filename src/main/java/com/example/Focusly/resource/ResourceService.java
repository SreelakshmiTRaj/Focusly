package com.example.Focusly.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepo;

    public Resource save(Resource resource) {
        return resourceRepo.save(resource);
    }

    public List<Resource> getAll() {
        return resourceRepo.findAll();
    }

    public List<Resource> getByTopic(String topic) {
        return resourceRepo.findByTopic(topic);
    }

    public void deleteResource(Long id) {
        resourceRepo.deleteById(id);
    }
}

