package com.example.springangularreddit.repository;

import com.example.springangularreddit.model.Comment;
import com.example.springangularreddit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}

//package com.example.springangularreddit.repository;
//
//import com.example.springangularreddit.model.Comment;
//import com.example.springangularreddit.model.Post;
//import com.example.springangularreddit.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface CommentRepository extends JpaRepository<Comment, Long> {
//
//    List<Comment> findByPost(Post post);
//
//    List<Comment> findAllByUser(User user);
//
//}
