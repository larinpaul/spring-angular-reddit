package com.example.springangularreddit.repository;

import com.example.springangularreddit.model.Comment;
import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);

}
