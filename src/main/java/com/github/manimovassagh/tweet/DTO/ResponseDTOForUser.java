package com.github.manimovassagh.tweet.DTO;

import com.github.manimovassagh.tweet.TweetManiClone;
import com.github.manimovassagh.tweet.entities.Tweet;
import com.github.manimovassagh.tweet.entities.User;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTOForUser <T>{
    private Status status;
    private UserResponseDTO user;
    private List<TweetResponseDTo> tweets;
}
