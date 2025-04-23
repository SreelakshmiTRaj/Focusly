package com.example.Focusly.moodcheckin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MoodCheckInService {

    @Autowired
    private MoodCheckInRepository moodCheckInRepository;

    // Add a new mood check-in
    public MoodCheckIn addMoodCheckIn(Long userId, MoodCheckIn checkIn) {
        checkIn.setUserId(userId);
        checkIn.setTimestamp(LocalDateTime.now());
        return moodCheckInRepository.save(checkIn);
    }

    // Get mood history for a specific user
    public List<MoodCheckIn> getMoodHistory(Long userId) {
        return moodCheckInRepository.findByUserId(userId);
    }
}

