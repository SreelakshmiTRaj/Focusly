package com.example.Focusly.admin;

import com.example.Focusly.user.User;
import com.example.Focusly.user.UserRepository;
import com.example.Focusly.studyplan.StudyPlan;
import com.example.Focusly.studyplan.StudyPlanRepository;
import com.example.Focusly.progress.UserProgress;
import com.example.Focusly.progress.UserProgressRepository;
import com.example.Focusly.badge.Badge;
import com.example.Focusly.badge.BadgeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final StudyPlanRepository studyPlanRepository;
    private final UserProgressRepository progressRepository;
    private final BadgeRepository badgeRepository;

    public AdminController(UserRepository userRepository, StudyPlanRepository studyPlanRepository,
                           UserProgressRepository progressRepository, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.studyPlanRepository = studyPlanRepository;
        this.progressRepository = progressRepository;
        this.badgeRepository = badgeRepository;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get study plans for a specific user
    @GetMapping("/user/{userId}/plans")
    public List<StudyPlan> getStudyPlans(@PathVariable Long userId) {
        return studyPlanRepository.findByUserId(userId);
    }

    // Get user progress summary
    @GetMapping("/user/{userId}/progress")
    public UserProgress getUserProgress(@PathVariable Long userId) {
        return progressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User progress not found"));
    }

    // Get badges for user
    @GetMapping("/user/{userId}/badges")
    public List<Badge> getUserBadges(@PathVariable Long userId) {
        return badgeRepository.findByUserId(userId);
    }

    // Delete a user badge (for admin override)
    @DeleteMapping("/user/{userId}/badge/{badgeId}")
    public void deleteBadge(@PathVariable Long badgeId) {
        badgeRepository.deleteById(badgeId);
    }

    // Reset user progress (admin action)
    @PostMapping("/user/{userId}/progress/reset")
    public void resetProgress(@PathVariable Long userId) {
        progressRepository.findByUserId(userId).ifPresent(progress -> {
            progress.setCompletedTasks(0);
            progress.setTotalTasks(0);
            progress.setCurrentStreak(0);
            progress.setLastCompletionDate(null);
            progressRepository.save(progress);
        });
    }
  

}

