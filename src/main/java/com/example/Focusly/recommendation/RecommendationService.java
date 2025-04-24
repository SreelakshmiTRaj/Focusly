package com.example.Focusly.recommendation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Focusly.studytask.StudyTask;
import com.example.Focusly.studytask.StudyTaskRepository;

@Service
public class RecommendationService {

    @Autowired
    private StudyTaskRepository studyTaskRepository;

    public Optional<StudyTask> getRecommendedTask(Long userId) {
        List<StudyTask> tasks = studyTaskRepository.findTopUncompletedTaskByUserIdOrderByDueDate(userId);
        return tasks.isEmpty() ? Optional.empty() : Optional.of(tasks.get(0));
    }
}
