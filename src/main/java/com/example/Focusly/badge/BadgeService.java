
package com.example.Focusly.badge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    // Assign a badge to a user
    public Badge assignBadge(Long userId, Badge badge) {
        badge.setUserId(userId);
        badge.setTimestamp(LocalDateTime.now());
        return badgeRepository.save(badge);
    }

    // Get all badges for a user
    public List<Badge> getBadgesForUser(Long userId) {
        return badgeRepository.findByUserId(userId);
    }
}
