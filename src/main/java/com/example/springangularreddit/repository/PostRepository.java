package com.example.springangularreddit.repository;

import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.model.Subreddit;
import com.example.springangularreddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);

}
