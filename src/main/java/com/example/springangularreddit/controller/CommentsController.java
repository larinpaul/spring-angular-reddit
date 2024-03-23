package com.example.springangularreddit.controller;

import com.example.springangularreddit.dto.CommentsDto;
import com.example.springangularreddit.exceptions.PostNotFoundException;
import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.repository.PostRepository;
import com.example.springangularreddit.repository.UserRepository;
import com.example.springangularreddit.service.AuthService;
import com.example.springangularreddit.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public void getAllCommentsForPost(@PathVariable Long postId) {
        ResponseEntity.status(OK).body(commentService.getAllCommentsForPost(postId));
    }



}


//package com.example.springangularreddit.controller;
//
//import com.example.springangularreddit.dto.CommentsDto;
//import com.example.springangularreddit.service.CommentService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.HttpStatus.CREATED;
//import static org.springframework.http.HttpStatus.OK;
//
//@RestController
//@RequestMapping("/api/comments")
//@AllArgsConstructor
//public class CommentsController {
//
//    private final CommentService commentService;
//
//    @PostMapping
//    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
//        commentService.save(commentsDto);
//        return new ResponseEntity<>(CREATED);
//    }
//
//    @GetMapping(params = "postId")
//    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@RequestParam Long postId) {
//        return ResponseEntity.status(OK)
//                .body(commentService.getAllCommentsForPost(postId));
//    }
//
//    @GetMapping(params = "userName")
//    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@RequestParam String userName) {
//        return ResponseEntity.status(OK)
//                .body(commentService.getAllCommentsForUser(userName));
//    }
//
//}
