// TODO This Implementation Code is supposed to be auto-generated
package com.example.springangularreddit.mapper;

import com.example.springangularreddit.dto.SubredditDto;
import com.example.springangularreddit.model.Subreddit;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2020-02-12T21:36:37+0100",
        comments = "version 1.3.1.Final, compiler: javac, encironment: Java 1.8.0_191 (or...)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
        if (subreddit == null) {
            return null;
        }

        SubredditDtoBuilder subredditDto = SubredditDto.builder();

        subredditDto.id( subreddit.getId() );
        subredditDto.name( subreddit.getName() );
        subredditDto.description( subreddit.getDescription() );

        subredditDto.numberOfPosts( mapPosts(subreddit.getPosts()) );

        return subredditDto.build();
    }

}
