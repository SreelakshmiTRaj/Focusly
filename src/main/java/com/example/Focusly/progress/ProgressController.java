package com.example.Focusly.progress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private UserProgressService progressService;

    @GetMapping("/{userId}/summary")
    public ResponseEntity<UserProgress> getUserProgress(@PathVariable Long userId) {
        return progressService.getProgressByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

