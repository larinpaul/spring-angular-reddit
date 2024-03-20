package com.example.springangularreddit.mapper;

import com.example.springangularreddit.dto.SubredditDto;
import com.example.springangularreddit.model.Post;
import com.example.springangularreddit.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    // compile the code in IntelliJIDEA by pressing Ctr+Shift+F9
    // and it is supposed to create SubredditMapperImpl, I guess...

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubredit(SubredditDto subredditDto);

}


//package com.example.springangularreddit.mapper;
//
//import com.example.springangularreddit.dto.SubredditDto;
//import com.example.springangularreddit.model.Post;
//import com.example.springangularreddit.model.Subreddit;
//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface SubredditMapper {
//
//    @Mapping(target = "numberOfPost", expression = "java(mapPost(subreddit.getPost()))")
//    SubredditDto mapSubredditToDto(Subreddit subreddit);
//
//    default Integer mapPosts(List<Post> numberOfPosts) {
//        return numberOfPosts.size();
//    }
//
//    @InheritInverseConfiguration
//    @Mapping(target = "posts", ignore = true)
//    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
//
//}
