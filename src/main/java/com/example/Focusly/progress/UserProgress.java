package com.example.Focusly.progress;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private int totalTasks;
    private int completedTasks;
    private int currentStreak;
    private LocalDate lastCompletionDate;

    public UserProgress() {}

    public UserProgress(Long userId, int totalTasks, int completedTasks, int currentStreak, LocalDate lastCompletionDate) {
        this.userId = userId;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.currentStreak = currentStreak;
        this.lastCompletionDate = lastCompletionDate;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public int getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

	public LocalDate getLastCompletionDate() {
		return lastCompletionDate;
	}

	public void setLastCompletionDate(LocalDate lastCompletionDate) {
		this.lastCompletionDate = lastCompletionDate;
	}    
}
