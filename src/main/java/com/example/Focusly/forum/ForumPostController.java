package com.example.Focusly.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forum")
@CrossOrigin(origins = "http://localhost:5173")
public class ForumPostController {

    @Autowired
    private ForumPostService forumPostService;

    @PostMapping("/create")
    public ForumPost createPost(@RequestBody ForumPost post) {
        return forumPostService.createPost(post);
    }

    @GetMapping("/all")
    public List<ForumPost> getAllPosts() {
        return forumPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Optional<ForumPost> getPostById(@PathVariable Long id) {
        return forumPostService.getPostById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        forumPostService.deletePost(id);
    }
}

