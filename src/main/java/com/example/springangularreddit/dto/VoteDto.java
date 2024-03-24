package com.example.springangularreddit.dto;

import com.example.springangularreddit.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}

//package com.example.springangularreddit.dto;
//
//import com.example.springangularreddit.model.VoteType;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class VoteDto {
//    private VoteType voteType;
//    private Long postId;
//}
