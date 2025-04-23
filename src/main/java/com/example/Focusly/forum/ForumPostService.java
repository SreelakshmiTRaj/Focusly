package com.example.Focusly.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumPostService {

    @Autowired
    private ForumPostRepository forumPostRepository;

    public ForumPost createPost(ForumPost post) {
        post.setCreatedAt(java.time.LocalDateTime.now());
        return forumPostRepository.save(post);
    }

    public List<ForumPost> getAllPosts() {
        return forumPostRepository.findAll();
    }

    public Optional<ForumPost> getPostById(Long id) {
        return forumPostRepository.findById(id);
    }

    public void deletePost(Long id) {
        forumPostRepository.deleteById(id);
    }
}
