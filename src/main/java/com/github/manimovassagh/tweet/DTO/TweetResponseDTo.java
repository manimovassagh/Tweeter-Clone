package com.github.manimovassagh.tweet.DTO;

import com.github.manimovassagh.tweet.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TweetResponseDTo {


    private Long tweetId;
    private String content;
    private String username;
    private String email;
    private LocalDateTime timestamp;

}
