package com.example.Focusly.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Find all notifications for a specific user
    List<Notification> findByUserId(Long userId);
}
