package com.example.springangularreddit.controller;

import com.example.springangularreddit.dto.PostRequest;
import com.example.springangularreddit.dto.PostResponse;
import com.example.springangularreddit.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity(CREATED);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/")
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/by-subreddit/{id}")
    public List<PostResponse> getPostsBySubreddit(Long id) {
        return postService.getPostsBySubreddit(id);
    }

    @GetMapping("/by-user/{name}")
    public List<PostResponse> getPostsByUsername(String username) {
        return postService.getPostsByUsername(username);
    }

}

//package com.example.springangularreddit.controller;
//
//import com.example.springangularreddit.dto.PostRequest;
//import com.example.springangularreddit.dto.PostResponse;
//import com.example.springangularreddit.service.PostService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.ResponseEntity.status;
//
//@RestController
//@RequestMapping("/api/posts")
//@AllArgsConstructor
//public class PostController {
//
//    private final PostService postService;
//
//    @PostMapping
//    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
//        postService.save(postRequest);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PostResponse>> getAllPosts() {
//        return status(HttpStatus.OK).body(postService.getAllPosts());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
//        return status(HttpStatus.OK).body(postService.getPost(id));
//    }
//
//    @GetMapping(params = "subredditId")
//    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@RequestParam Long subredditId) {
//        return status(HttpStatus.OK).body(postService.getPostsBySubreddit(subredditId));
//    }
//
//    @GetMapping(params = "username")
//    public ResponseEntity<List<PostResponse>> getPostsByUsername(@RequestParam String username) {
//        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
//    }
//}