package com.example.Focusly.moodcheckin;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MoodCheckInRepository extends JpaRepository<MoodCheckIn, Long> {

    // Find all mood check-ins for a specific user
    List<MoodCheckIn> findByUserId(Long userId);
}
