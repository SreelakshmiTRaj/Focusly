package com.example.Focusly.progress;

import com.example.Focusly.badge.Badge;
import com.example.Focusly.badge.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserProgressService {

    @Autowired
    private UserProgressRepository progressRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    public void updateProgress(Long userId, boolean taskCompleted) {
        UserProgress progress = progressRepository.findByUserId(userId).orElseGet(() -> {
            UserProgress up = new UserProgress(userId, 0, 0, 0, null);
            return progressRepository.save(up);
        });

        progress.setTotalTasks(progress.getTotalTasks() + 1);
        if (taskCompleted) {
            progress.setCompletedTasks(progress.getCompletedTasks() + 1);
            updateStreak(progress);
            checkAndAwardBadge(progress);
        }

        progressRepository.save(progress);
    }

    private void updateStreak(UserProgress progress) {
        LocalDate today = LocalDate.now();
        if (today.minusDays(1).equals(progress.getLastCompletionDate())) {
            progress.setCurrentStreak(progress.getCurrentStreak() + 1);
        } else if (!today.equals(progress.getLastCompletionDate())) {
            progress.setCurrentStreak(1);
        }
        progress.setLastCompletionDate(today);
    }

    private void checkAndAwardBadge(UserProgress progress) {
        if (progress.getCurrentStreak() == 7) {
            Badge badge = new Badge();
            badge.setUserId(progress.getUserId());
            badge.setBadgeName("Weekly Warrior");
            badge.setDescription("Completed tasks 7 days in a row!");
            badge.setCriteria("7-day streak");
            badge.setTimestamp(LocalDate.now().atStartOfDay());
            badgeRepository.save(badge);
        }
    }

    public Optional<UserProgress> getProgressByUserId(Long userId) {
        return progressRepository.findByUserId(userId);
    }
}

