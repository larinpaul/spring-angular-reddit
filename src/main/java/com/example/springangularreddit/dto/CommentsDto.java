package com.example.springangularreddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private Long postId;
    private Instant createDate;
    private String text;
    private String userName;
}

//package com.example.springangularreddit.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.Instant;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class CommentsDto {
//    private Long id;
//    private Long postId;
//    private Instant createDate;
//    @NotBlank
//    private String text;
//    private String userName;
//}
