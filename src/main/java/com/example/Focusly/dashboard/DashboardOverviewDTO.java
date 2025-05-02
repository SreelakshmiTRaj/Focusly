package com.example.Focusly.dashboard;

import com.example.Focusly.studyplan.StudyPlan;

import java.util.List;

public class DashboardOverviewDTO {
 private List<StudyPlan> studyPlans;
 private long totalTasks;
 private long completedTasks;

 public DashboardOverviewDTO(List<StudyPlan> studyPlans, long totalTasks, long completedTasks) {
     this.studyPlans = studyPlans;
     this.totalTasks = totalTasks;
     this.completedTasks = completedTasks;
 }

 public List<StudyPlan> getStudyPlans() {
     return studyPlans;
 }

 public long getTotalTasks() {
     return totalTasks;
 }

 public long getCompletedTasks() {
     return completedTasks;
 }
}

