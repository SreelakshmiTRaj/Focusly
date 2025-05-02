package com.example.Focusly.studytask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studytask")
@CrossOrigin(origins = "http://localhost:5173")
public class StudyTaskController {

    @Autowired
    private StudyTaskService studyTaskService;

    @PostMapping("/create/{studyPlanId}")
    public StudyTask createTask(@PathVariable Long studyPlanId, @RequestBody StudyTask task) {
        return studyTaskService.createTask(studyPlanId, task);
    }

    @GetMapping("/plan/{studyPlanId}")
    public List<StudyTask> getTasksByPlan(@PathVariable Long studyPlanId) {
        return studyTaskService.getTasksByStudyPlan(studyPlanId);
    }

    @GetMapping("/{taskId}")
    public Optional<StudyTask> getTaskById(@PathVariable Long taskId) {
        return studyTaskService.getTaskById(taskId);
    }

    @DeleteMapping("/delete/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        studyTaskService.deleteTask(taskId);
    }

    @PutMapping("/complete/{taskId}")
    public StudyTask completeTask(@PathVariable Long taskId) {
        return studyTaskService.markAsCompleted(taskId);
    }
}
