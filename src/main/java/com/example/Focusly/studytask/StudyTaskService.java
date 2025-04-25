package com.example.Focusly.studytask;

import com.example.Focusly.studyplan.StudyPlan;
import com.example.Focusly.studyplan.StudyPlanRepository;
import com.example.Focusly.progress.UserProgressService; // ⬅️ Add this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyTaskService {

    @Autowired
    private StudyTaskRepository studyTaskRepository;

    @Autowired
    private StudyPlanRepository studyPlanRepository;

    @Autowired
    private UserProgressService userProgressService; // ⬅️ Injected

    public StudyTask createTask(Long studyPlanId, StudyTask task) {
        Optional<StudyPlan> studyPlanOpt = studyPlanRepository.findById(studyPlanId);
        if (studyPlanOpt.isPresent()) {
            task.setStudyPlan(studyPlanOpt.get());
            return studyTaskRepository.save(task);
        }
        throw new RuntimeException("Study plan not found");
    }

    public List<StudyTask> getTasksByStudyPlan(Long studyPlanId) {
        return studyTaskRepository.findByStudyPlanId(studyPlanId);
    }

    public Optional<StudyTask> getTaskById(Long taskId) {
        return studyTaskRepository.findById(taskId);
    }

    public void deleteTask(Long taskId) {
        studyTaskRepository.deleteById(taskId);
    }

    public StudyTask markAsCompleted(Long taskId) {
        StudyTask task = studyTaskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        StudyTask updatedTask = studyTaskRepository.save(task);

        Long userId = task.getStudyPlan().getUser().getId();
        userProgressService.updateProgress(userId, true);

        return updatedTask;
    }
}
