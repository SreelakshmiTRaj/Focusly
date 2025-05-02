package com.example.Focusly.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
@CrossOrigin(origins = "http://localhost:5173")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/add")
    public Resource add(@RequestBody Resource resource) {
        return resourceService.save(resource);
    }

    @GetMapping("/all")
    public List<Resource> getAll() {
        return resourceService.getAll();
    }

    @GetMapping("/topic/{topic}")
    public List<Resource> getByTopic(@PathVariable String topic) {
        return resourceService.getByTopic(topic);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }
}
