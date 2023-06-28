package com.github.manimovassagh.tweet.DTO;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDTO {
    private String username;
    private String email;

}
