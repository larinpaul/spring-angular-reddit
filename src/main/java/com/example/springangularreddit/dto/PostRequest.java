package com.example.springangularreddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    private String subredditName;
    private String postName;
    private String url;
    private String description;
}


//package com.example.springangularreddit.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class PostRequest {
//    private Long postId;
//    private String subredditName;
//    @NotBlank(message = "Post Name cannot be empty or Null")
//    private String postName;
//    private String url;
//    private String description;
//}
