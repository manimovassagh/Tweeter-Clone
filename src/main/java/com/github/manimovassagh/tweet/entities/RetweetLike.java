package com.github.manimovassagh.tweet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "retweetlikes")
public class RetweetLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retweetLikeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "retweet_id")
    private Retweet retweet;

}
