package com.example.springangularreddit.service;

import com.example.springangularreddit.dto.SubredditDto;
import com.example.springangularreddit.mapper.SubredditMapper;
import com.example.springangularreddit.model.Subreddit;
import com.example.springangularreddit.repository.SubredditRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubredit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapToDto)
                .collect(toList());
    }

}


//package com.example.springangularreddit.service;
//
//import com.example.springangularreddit.dto.SubredditDto;
//import com.example.springangularreddit.exceptions.SpringRedditException;
//import com.example.springangularreddit.mapper.SubredditMapper;
//import com.example.springangularreddit.model.Subreddit;
//import com.example.springangularreddit.repository.SubredditRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
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
//public class SubredditService {
//
//    private final SubredditRepository subredditRepository;
//    private final SubredditMapper subredditMapper;
//
//    @Transactional
//    public SubredditDto save(SubredditDto subredditDto) {
//        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
//        subredditDto.setId(save.getId());
//        return subredditDto;
//    }
//
//    @Transactional(readOnly = true)
//    public List<SubredditDto> getAll() {
//        return subredditRepository.findAll()
//                .stream()
//                .map(subredditMapper::mapSubredditToDto)
//                .collect(toList());
//    }
//
//    public SubredditDto getSubreddit(Long id) {
//        Subreddit subreddit = subredditRepository.findById(id)
//                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
//        return subredditMapper.mapSubredditToDto(subreddit);
//    }
//}
