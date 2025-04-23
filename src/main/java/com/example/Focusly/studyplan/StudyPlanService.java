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

    // Delete a study plan
    public void deleteStudyPlan(Long studyPlanId) {
        studyPlanRepository.deleteById(studyPlanId);
    }
}
