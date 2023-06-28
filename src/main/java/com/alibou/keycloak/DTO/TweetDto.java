package com.alibou.keycloak.DTO;


import com.alibou.keycloak.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TweetDto {
    private String content;

}
