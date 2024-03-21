package com.example.springangularreddit.service;

import com.example.springangularreddit.dto.PostRequest;
import com.example.springangularreddit.dto.PostResponse;
import com.example.springangularreddit.exceptions.PostNotFoundException;
import com.example.springangularreddit.exceptions.SubredditNotFoundException;
import com.example.springangularreddit.mapper.PostMapper;
import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.model.Subreddit;
import com.example.springangularreddit.model.User;
import com.example.springangularreddit.repository.PostRepository;
import com.example.springangularreddit.repository.SubredditRepository;
import com.example.springangularreddit.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();

        return postMapper.map(postRequest, subreddit, currentUser);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

}


//package com.example.springangularreddit.service;
//
//import com.example.springangularreddit.dto.PostRequest;
//import com.example.springangularreddit.dto.PostResponse;
//import com.example.springangularreddit.exceptions.PostNotFoundException;
//import com.example.springangularreddit.exceptions.SubredditNotFoundException;
//import com.example.springangularreddit.mapper.PostMapper;
//import com.example.springangularreddit.model.Post;
//import com.example.springangularreddit.model.Subreddit;
//import com.example.springangularreddit.model.User;
//import com.example.springangularreddit.repository.PostRepository;
//import com.example.springangularreddit.repository.SubredditRepository;
//import com.example.springangularreddit.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static java.util.stream.Collectors.toList;
//
//@Service
//@AllArgsConstructor
//@Slf4j
//@Transactional
//public class PostService {
//
//    private final PostRepository postRepository;
//    private final SubredditRepository subredditRepository;
//    private final UserRepository userRepository;
//    private final AuthService authService;
//    private final PostMapper postMapper;
//
//    public void save(PostRequest postRequest) {
//        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
//                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
//        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
//    }
//
//    @Transactional(readOnly = true)
//    public PostResponse getPost(Long id) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new PostNotFoundException(id.toString()));
//        return postMapper.mapToDto(post);
//    }
//
//    @Transactional(readOnly = true)
//    public List<PostResponse> getAllPosts() {
//        return postRepository.findAll()
//                .stream()
//                .map(postMapper::mapToDto)
//                .collect(toList());
//    }
//
//    @Transactional(readOnly = true)
//    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
//        Subreddit subreddit = subredditRepository.findById(subredditId)
//                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
//        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
//        return posts.stream().map(postMapper::mapToDto).collect(toList());
//    }
//
//    @Transactional(readOnly = true)
//    public List<PostResponse> getPostsByUsername(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//        return postRepository.findByUser(user)
//                .stream()
//                .map(postMapper::mapToDto)
//                .collect(toList());
//    }
//}