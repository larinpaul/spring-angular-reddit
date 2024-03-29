package com.example.springangularreddit.repository;

import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.model.User;
import com.example.springangularreddit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}

//package com.example.springangularreddit.repository;
//
//import com.example.springangularreddit.model.Post;
//import com.example.springangularreddit.model.User;
//import com.example.springangularreddit.model.Vote;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface VoteRepository extends JpaRepository<Vote, Long> {
//    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
//}
