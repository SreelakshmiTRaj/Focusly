package com.example.Focusly.recommendation;

import com.example.Focusly.studytask.StudyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/next-task/{userId}")
    public ResponseEntity<?> getNextRecommendedTask(@PathVariable Long userId) {
        Optional<StudyTask> task = recommendationService.getRecommendedTask(userId);

        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No uncompleted tasks found.");
        }
    }
}
