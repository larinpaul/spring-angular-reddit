package com.example.springangularreddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}


//package com.example.springangularreddit.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class SubredditDto {
//    private Long id;
//    @NotBlank(message = "Community name is required")
//    private String name;
//    @NotBlank(message = "Description is required")
//    private String description;
//    private Integer numberOfPosts;
//}
