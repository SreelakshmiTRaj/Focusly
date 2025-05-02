package com.example.Focusly.dashboard;

public class AdminDashboardDTO {
    private long totalUsers;
    private long totalPlans;
    private long totalTasks;
    private long totalForumPosts;
    private long totalBadges;

    public AdminDashboardDTO(long totalUsers, long totalPlans, long totalTasks, long totalForumPosts, long totalBadges) {
        this.totalUsers = totalUsers;
        this.totalPlans = totalPlans;
        this.totalTasks = totalTasks;
        this.totalForumPosts = totalForumPosts;
        this.totalBadges = totalBadges;
    }

    // Getters
    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalPlans() {
        return totalPlans;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public long getTotalForumPosts() {
        return totalForumPosts;
    }

    public long getTotalBadges() {
        return totalBadges;
    }
}
