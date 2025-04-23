package com.example.Focusly.studyplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studyplan")
public class StudyPlanController {

    @Autowired
    private StudyPlanService studyPlanService;

    // Create or update a study plan
    @PostMapping("/create/{userId}")
    public StudyPlan createStudyPlan(@PathVariable Long userId, @RequestBody StudyPlan studyPlan) {
        return studyPlanService.createOrUpdateStudyPlan(userId, studyPlan);
    }

    // Get all study plans for a user
    @GetMapping("/user/{userId}")
    public List<StudyPlan> getStudyPlansByUser(@PathVariable Long userId) {
        return studyPlanService.getStudyPlansByUser(userId);
    }

    // Get a specific study plan by ID
    @GetMapping("/{studyPlanId}")
    public Optional<StudyPlan> getStudyPlanById(@PathVariable Long studyPlanId) {
        return studyPlanService.getStudyPlanById(studyPlanId);
    }

    // Delete a study plan
    @DeleteMapping("/delete/{studyPlanId}")
    public void deleteStudyPlan(@PathVariable Long studyPlanId) {
        studyPlanService.deleteStudyPlan(studyPlanId);
    }
}
