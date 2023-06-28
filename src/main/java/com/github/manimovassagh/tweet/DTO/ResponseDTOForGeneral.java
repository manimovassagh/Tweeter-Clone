package com.github.manimovassagh.tweet.DTO;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTOForGeneral<T>{
    private Status status;
    private List<TweetResponseDTo> tweets;
}
