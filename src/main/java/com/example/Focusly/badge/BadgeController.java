package com.example.Focusly.badge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badge")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    // Assign a badge to a user
    @PostMapping("/assign/{userId}")
    public Badge assignBadge(@PathVariable Long userId, @RequestBody Badge badge) {
        return badgeService.assignBadge(userId, badge);
    }

    // Get all badges for a user
    @GetMapping("/{userId}")
    public List<Badge> getBadgesForUser(@PathVariable Long userId) {
        return badgeService.getBadgesForUser(userId);
    }
}
