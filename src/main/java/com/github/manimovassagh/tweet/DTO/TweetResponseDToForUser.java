package com.github.manimovassagh.tweet.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TweetResponseDToForUser {


    private Long tweetId;
    private String content;
    private LocalDateTime timestamp;

}
