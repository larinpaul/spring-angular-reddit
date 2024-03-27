package com.example.springangularreddit.service;

import com.example.springangularreddit.dto.VoteDto;
import com.example.springangularreddit.exceptions.PostNotFoundException;
import com.example.springangularreddit.exceptions.SpringRedditException;
import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.model.Vote;
import com.example.springangularreddit.repository.PostRepository;
import com.example.springangularreddit.repository.VoteRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

import static com.example.springangularreddit.model.VoteType.UPVOTE;

public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType() // If I have upvoted a Post, I should not be able to upvote again
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }

}


//package com.example.springangularreddit.service;
//
//import com.example.springangularreddit.dto.VoteDto;
//import com.example.springangularreddit.exceptions.PostNotFoundException;
//import com.example.springangularreddit.exceptions.SpringRedditException;
//import com.example.springangularreddit.model.Post;
//import com.example.springangularreddit.model.Vote;
//import com.example.springangularreddit.repository.PostRepository;
//import com.example.springangularreddit.repository.VoteRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static com.example.springangularreddit.model.VoteType.UPVOTE;
//
//@Service
//@AllArgsConstructor
//public class VoteService {
//
//    private final VoteRepository voteRepository;
//    private final PostRepository postRepository;
//    private final AuthService authService;
//
//    @Transactional
//    public void vote(VoteDto voteDto) {
//        Post post = postRepository.findById(voteDto.getPostId())
//                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
//        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
//        if (voteByPostAndUser.isPresent() &&
//                voteByPostAndUser.get().getVoteType()
//                        .equals(voteDto.getVoteType())) {
//            throw new SpringRedditException("You have already "
//                    + voteDto.getVoteType() + "'d for this post");
//        }
//        if (UPVOTE.equals(voteDto.getVoteType())) {
//            post.setVoteCount(post.getVoteCount() + 1);
//        } else {
//            post.setVoteCount(post.getVoteCount() - 1);
//        }
//        voteRepository.save(mapToVote(voteDto, post));
//        postRepository.save(post);
//    }
//
//    private Vote mapToVote(VoteDto voteDto, Post post) {
//        return Vote.builder()
//                .voteType(voteDto.getVoteType())
//                .post(post)
//                .user(authService.getCurrentUser())
//                .build();
//    }
//}