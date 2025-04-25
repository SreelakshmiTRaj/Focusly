package com.example.Focusly.studyplan;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {
    List<StudyPlan> findByUserId(Long userId);
    
    List<StudyPlan> findByGoalContainingIgnoreCase(String goal);
}

