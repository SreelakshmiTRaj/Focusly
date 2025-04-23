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

    // Mark current course as completed
    @PostMapping("/complete/{studyPlanId}")
    public StudyPlan completeCurrentCourse(@PathVariable Long studyPlanId) {
        return studyPlanService.completeCurrentCourse(studyPlanId);
    }

    // Move to the next course in the study plan
    @PostMapping("/next/{studyPlanId}")
    public StudyPlan moveToNextCourse(@PathVariable Long studyPlanId) {
        if (studyPlanService.canMoveToNextCourse(studyPlanId)) {
            return studyPlanService.moveToNextCourse(studyPlanId);
        } else {
            throw new RuntimeException("You need to complete the current course first.");
        }
    }

    // Delete a study plan
    @DeleteMapping("/delete/{studyPlanId}")
    public void deleteStudyPlan(@PathVariable Long studyPlanId) {
        studyPlanService.deleteStudyPlan(studyPlanId);
    }
}
