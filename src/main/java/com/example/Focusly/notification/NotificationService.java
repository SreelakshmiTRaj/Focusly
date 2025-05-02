package com.example.Focusly.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Focusly.user.User;
import com.example.Focusly.user.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private UserRepository userRepository;


    // Send a notification to a user
    public Notification sendNotification(Long userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false); 
        notification.setDeleted(false);
        notification.setType("INFO");
        return notificationRepository.save(notification);
    }

    // Get all notifications for a user
    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Mark a notification as read
    public Notification markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setRead(true);
            return notificationRepository.save(notification);
        }
        return null;
    }
    
    public List<Notification> sendToAll(String message, String type) {
        List<User> users = userRepository.findAll(); // Inject UserRepository
        List<Notification> sentNotifications = new ArrayList<>();

        for (User user : users) {
            Notification notification = new Notification();
            notification.setUserId(user.getId());
            notification.setMessage(message);
            notification.setTimestamp(LocalDateTime.now());
            notification.setRead(false);
            notification.setDeleted(false);
            notification.setType(type != null ? type : "INFO");

            sentNotifications.add(notificationRepository.save(notification));
        }

        return sentNotifications;
    }


}
