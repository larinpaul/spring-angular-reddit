package com.example.springangularreddit.controller;

import com.example.springangularreddit.dto.VoteDto;
import com.example.springangularreddit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        voteService.vote(voteDto);
        return new ResponseEntity<>(OK);
    }

}


//package com.example.springangularreddit.controller;
//
//import com.example.springangularreddit.dto.VoteDto;
//import com.example.springangularreddit.service.VoteService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/votes")
//@AllArgsConstructor
//public class VoteController {
//
//    private final VoteService voteService;
//
//    @PostMapping
//    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
//        voteService.vote(voteDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//}
