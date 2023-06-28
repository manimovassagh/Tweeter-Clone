package com.github.manimovassagh.tweet.DTO;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDTO <T>{
    private Status status;
    private T response;

}
