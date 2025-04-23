package com.example.Focusly.studyplan;

import com.example.Focusly.user.User;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    
    @ElementCollection
    private List<String> subjects; 

    private String goal;

    @Enumerated(EnumType.STRING)
    private PlanStatus completionStatus;
    
    private Integer currentCourseIndex;

    public enum PlanStatus {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public PlanStatus getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(PlanStatus completionStatus) {
		this.completionStatus = completionStatus;
	}    
	
	 public Integer getCurrentCourseIndex() {
	        return currentCourseIndex;
	    }

	    public void setCurrentCourseIndex(Integer currentCourseIndex) {
	        this.currentCourseIndex = currentCourseIndex;
	    }
}
