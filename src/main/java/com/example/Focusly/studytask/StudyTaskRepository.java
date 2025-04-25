package com.example.Focusly.studytask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyTaskRepository extends JpaRepository<StudyTask, Long> {
    List<StudyTask> findByStudyPlanId(Long studyPlanId);
    
    @Query("SELECT t FROM StudyTask t WHERE t.studyPlan.user.id = :userId AND t.completed = false ORDER BY t.dueDate ASC")
    List<StudyTask> findTopUncompletedTaskByUserIdOrderByDueDate(@Param("userId") Long userId);
    
    List<StudyTask> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

}
