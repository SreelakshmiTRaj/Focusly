package com.example.Focusly.admin;

import com.example.Focusly.user.User;
import com.example.Focusly.user.UserRepository;
import com.example.Focusly.studyplan.StudyPlan;
import com.example.Focusly.studyplan.StudyPlanRepository;
import com.example.Focusly.studytask.StudyTask;
import com.example.Focusly.studytask.StudyTaskRepository;
import com.example.Focusly.moodcheckin.MoodCheckIn;
import com.example.Focusly.moodcheckin.MoodCheckInRepository;
import com.example.Focusly.forum.ForumPost;
import com.example.Focusly.forum.ForumPostRepository;
import com.example.Focusly.badge.Badge;
import com.example.Focusly.badge.BadgeRepository;
import com.example.Focusly.notification.Notification;
import com.example.Focusly.notification.NotificationRequest;
import com.example.Focusly.notification.NotificationService;
import com.example.Focusly.dashboard.AdminDashboardDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;
    private final StudyPlanRepository studyPlanRepository;
    private final StudyTaskRepository studyTaskRepository;
    private final MoodCheckInRepository moodCheckInRepository;
    private final ForumPostRepository forumPostRepository;
    private final BadgeRepository badgeRepository;
    private final NotificationService notificationService;

    public AdminController(UserRepository userRepository,
                           StudyPlanRepository studyPlanRepository,
                           StudyTaskRepository studyTaskRepository,
                           MoodCheckInRepository moodCheckInRepository,
                           ForumPostRepository forumPostRepository,
                           BadgeRepository badgeRepository,
                           NotificationService notificationService) {
        this.userRepository = userRepository;
        this.studyPlanRepository = studyPlanRepository;
        this.studyTaskRepository = studyTaskRepository;
        this.moodCheckInRepository = moodCheckInRepository;
        this.forumPostRepository = forumPostRepository;
        this.badgeRepository = badgeRepository;
        this.notificationService = notificationService;
    }

    // --- User Management ---
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestParam String role) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(role); // Make sure the `User` entity has a `setRole()` method
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    // --- Study Plans ---
    @GetMapping("/studyplans")
    public List<StudyPlan> getAllStudyPlans() {
        return studyPlanRepository.findAll();
    }

    @DeleteMapping("/studyplans/{id}")
    public ResponseEntity<?> deleteStudyPlan(@PathVariable Long id) {
        studyPlanRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // --- Study Tasks ---
    @GetMapping("/studytasks")
    public List<StudyTask> getAllStudyTasks() {
        return studyTaskRepository.findAll();
    }

    @DeleteMapping("/studytasks/{id}")
    public ResponseEntity<?> deleteStudyTask(@PathVariable Long id) {
        studyTaskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // --- Mood Check-ins ---
    @GetMapping("/moodcheckins")
    public List<MoodCheckIn> getAllMoodCheckIns() {
        return moodCheckInRepository.findAll();
    }

    // --- Forum Moderation ---
    @GetMapping("/forum/posts")
    public List<ForumPost> getAllForumPosts() {
        return forumPostRepository.findAll();
    }

    @DeleteMapping("/forum/posts/{id}")
    public ResponseEntity<?> deleteForumPost(@PathVariable Long id) {
        forumPostRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // --- Badge Management ---
    @GetMapping("/badges")
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    @PostMapping("/badges")
    public Badge createBadge(@RequestBody Badge badge) {
        return badgeRepository.save(badge);
    }

    @DeleteMapping("/badges/{id}")
    public ResponseEntity<?> deleteBadge(@PathVariable Long id) {
        badgeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // --- Notifications ---
    @PostMapping("/notifications")
    public ResponseEntity<?> sendNotificationToAll(@RequestBody NotificationRequest request) {
        List<Notification> notifications = notificationService.sendToAll(request.getMessage(), request.getType());
        return ResponseEntity.ok(notifications);
    }


    // --- Analytics / Dashboard Summary ---
    @GetMapping("/summary")
    public AdminDashboardDTO getPlatformStats() {
        long totalUsers = userRepository.count();
        long totalPlans = studyPlanRepository.count();
        long totalTasks = studyTaskRepository.count();
        long totalPosts = forumPostRepository.count();
        long totalBadges = badgeRepository.count();
        return new AdminDashboardDTO(totalUsers, totalPlans, totalTasks, totalPosts, totalBadges);
    }
}
