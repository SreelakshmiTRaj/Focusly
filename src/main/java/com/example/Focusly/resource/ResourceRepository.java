package com.example.Focusly.resource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByTopic(String topic);
    List<Resource> findByLocked(boolean locked);
}
