package com.example.Focusly.moodcheckin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moodcheckin")
@CrossOrigin(origins = "http://localhost:5173")
public class MoodCheckInController {

    @Autowired
    private MoodCheckInService moodCheckInService;

    // Create a mood check-in for a user
    @PostMapping("/{userId}")
    public MoodCheckIn addMoodCheckIn(@PathVariable Long userId, @RequestBody MoodCheckIn checkIn) {
        return moodCheckInService.addMoodCheckIn(userId, checkIn);
    }

    // Get all mood check-ins for a specific user
    @GetMapping("/history/{userId}")
    public List<MoodCheckIn> getMoodHistory(@PathVariable Long userId) {
        return moodCheckInService.getMoodHistory(userId);
    }
}
