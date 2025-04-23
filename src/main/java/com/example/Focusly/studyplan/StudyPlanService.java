package com.example.Focusly.studyplan;

import com.example.Focusly.user.User;
import com.example.Focusly.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyPlanService {

    @Autowired
    private StudyPlanRepository studyPlanRepository;

    @Autowired
    private UserService userService;

    // Create or update a study plan
    public StudyPlan createOrUpdateStudyPlan(Long userId, StudyPlan studyPlan) {
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            studyPlan.setUser(user);
            studyPlan.setCurrentCourseIndex(0); // Start at the first course
            return studyPlanRepository.save(studyPlan);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    // Get all study plans for a user
    public List<StudyPlan> getStudyPlansByUser(Long userId) {
        return studyPlanRepository.findByUserId(userId);
    }

    // Get a specific study plan by ID
    public Optional<StudyPlan> getStudyPlanById(Long studyPlanId) {
        return studyPlanRepository.findById(studyPlanId);
    }

    // Check if user can move to the next course
    public boolean canMoveToNextCourse(Long studyPlanId) {
        Optional<StudyPlan> studyPlanOpt = studyPlanRepository.findById(studyPlanId);
        if (studyPlanOpt.isPresent()) {
            StudyPlan studyPlan = studyPlanOpt.get();
            return studyPlan.getCompletionStatus() == StudyPlan.PlanStatus.COMPLETED;
        }
        return false;
    }

    // Move to the next course in the study plan
    public StudyPlan moveToNextCourse(Long studyPlanId) {
        Optional<StudyPlan> studyPlanOpt = studyPlanRepository.findById(studyPlanId);
        if (studyPlanOpt.isPresent()) {
            StudyPlan studyPlan = studyPlanOpt.get();
            if (studyPlan.getCompletionStatus() == StudyPlan.PlanStatus.COMPLETED) {
                int nextCourseIndex = studyPlan.getCurrentCourseIndex() + 1;
                if (nextCourseIndex < studyPlan.getSubjects().size()) {
                    studyPlan.setCurrentCourseIndex(nextCourseIndex);
                    studyPlan.setCompletionStatus(StudyPlan.PlanStatus.NOT_STARTED);
                    studyPlanRepository.save(studyPlan);
                }
            }
        }
        return studyPlanOpt.orElseThrow(() -> new RuntimeException("Study plan not found"));
    }

    // Mark current course as completed
    public StudyPlan completeCurrentCourse(Long studyPlanId) {
        Optional<StudyPlan> studyPlanOpt = studyPlanRepository.findById(studyPlanId);
        if (studyPlanOpt.isPresent()) {
            StudyPlan studyPlan = studyPlanOpt.get();
            studyPlan.setCompletionStatus(StudyPlan.PlanStatus.COMPLETED);
            studyPlanRepository.save(studyPlan);
        }
        return studyPlanOpt.orElseThrow(() -> new RuntimeException("Study plan not found"));
    }

    // Delete a study plan
    public void deleteStudyPlan(Long studyPlanId) {
        studyPlanRepository.deleteById(studyPlanId);
    }
}
