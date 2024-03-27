package com.example.springangularreddit.mapper;

import com.example.springangularreddit.dto.PostRequest;
import com.example.springangularreddit.dto.PostResponse;
import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.model.Subreddit;
import com.example.springangularreddit.model.User;
import com.example.springangularreddit.repository.CommentRepository;
import com.example.springangularreddit.repository.VoteRepository;
import com.example.springangularreddit.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

}


//package com.example.springangularreddit.mapper;
//
//import com.example.springangularreddit.dto.PostRequest;
//import com.example.springangularreddit.dto.PostResponse;
//import com.example.springangularreddit.model.Post;
//import com.example.springangularreddit.model.Subreddit;
//import com.example.springangularreddit.model.User;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface PostMapper {
//    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
//    @Mapping(target = "description", source = "postRequest.description")
//    Post map(PostRequest postRequest, Subreddit subreddit, User user);
//
//    @Mapping(target = "id", source = "postId")
////    // We can remove the @Mapping when the source and the target name are the same
////    @Mapping(target = "postName", source = "postName")
////    @Mapping(target = "description", source = "description")
////    @Mapping(target = "url", source = "url")
//    @Mapping(target = "subredditName", source = "subreddit.name")
//    @Mapping(target = "userName", source = "user.username")
//    PostResponse mapToDto(Post post);
//
//    // Ctrl + Shift + F9 to compile and create Impl
//
//}
//
//
////package com.example.springangularreddit.mapper;
////
////import com.github.marlonlom.utilities.timeago.TimeAgo;
////import com.example.springangularreddit.dto.PostRequest;
////import com.example.springangularreddit.dto.PostResponse;
////import com.example.springangularreddit.model.*;
////import com.example.springangularreddit.repository.CommentRepository;
////import com.example.springangularreddit.repository.VoteRepository;
////import com.example.springangularreddit.service.AuthService;
////import org.mapstruct.Mapper;
////import org.mapstruct.Mapping;
////import org.springframework.beans.factory.annotation.Autowired;
////
////import java.util.Optional;
////
////import static com.example.springangularreddit.model.VoteType.DOWNVOTE;
////import static com.example.springangularreddit.model.VoteType.UPVOTE;
////
////@Mapper(componentModel = "spring")
////public abstract class PostMapper {
////
////    @Autowired
////    private CommentRepository commentRepository;
////    @Autowired
////    private VoteRepository voteRepository;
////    @Autowired
////    private AuthService authService;
////
////
////    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
////    @Mapping(target = "description", source = "postRequest.description")
////    @Mapping(target = "subreddit", source = "subreddit")
////    @Mapping(target = "voteCount", constant = "0")
////    @Mapping(target = "user", source = "user")
////    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);
////
////    @Mapping(target = "id", source = "postId")
////    @Mapping(target = "subredditName", source = "subreddit.name")
////    @Mapping(target = "userName", source = "user.username")
////    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
////    @Mapping(target = "duration", expression = "java(getDuration(post))")
////    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
////    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
////    public abstract PostResponse mapToDto(Post post);
////
////    Integer commentCount(Post post) {
////        return commentRepository.findByPost(post).size();
////    }
////
////    String getDuration(Post post) {
////        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
////    }
////
////    boolean isPostUpVoted(Post post) {
////        return checkVoteType(post, UPVOTE);
////    }
////
////    boolean isPostDownVoted(Post post) {
////        return checkVoteType(post, DOWNVOTE);
////    }
////
////    private boolean checkVoteType(Post post, VoteType voteType) {
////        if (authService.isLoggedIn()) {
////            Optional<Vote> voteForPostByUser =
////                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
////                            authService.getCurrentUser());
////            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
////                    .isPresent();
////        }
////        return false;
////    }
////
////}
