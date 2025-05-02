package com.example.Focusly.dashboard;

import com.example.Focusly.studyplan.StudyPlanRepository;
import com.example.Focusly.studytask.StudyTaskRepository;
import com.example.Focusly.studytask.StudyTask;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

 private final StudyPlanRepository studyPlanRepository;
 private final StudyTaskRepository studyTaskRepository;

 public DashboardController(StudyPlanRepository studyPlanRepository, StudyTaskRepository studyTaskRepository) {
     this.studyPlanRepository = studyPlanRepository;
     this.studyTaskRepository = studyTaskRepository;
 }

 @GetMapping("/overview")
 public DashboardOverviewDTO getDashboardOverview(@RequestParam Long userId) {
     var studyPlans = studyPlanRepository.findByUserId(userId);
     var allTasks = studyTaskRepository.findAllByStudyPlanUserId(userId);

     long totalTasks = allTasks.size();
     long completedTasks = allTasks.stream().filter(StudyTask::isCompleted).count();

     return new DashboardOverviewDTO(studyPlans, totalTasks, completedTasks);
 }
}

