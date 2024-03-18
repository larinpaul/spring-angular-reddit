package com.example.springangularreddit.controller;

import com.example.springangularreddit.dto.SubredditDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    @PostMapping
    public void createSubreddit(@RequestBody SubredditDto subredditDto) {

    }

}




//package com.example.springangularreddit.controller;
//
//import com.example.springangularreddit.dto.SubredditDto;
//import com.example.springangularreddit.service.SubredditService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/subreddit")
//@AllArgsConstructor
//@Slf4j
//public class SubredditController {
//
//    private final SubredditService subredditService;
//
//    @PostMapping
//    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(subredditService.save(subredditDto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(subredditService.getAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable Long id) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(subredditService.getSubreddit(id));
//    }
//
//}
