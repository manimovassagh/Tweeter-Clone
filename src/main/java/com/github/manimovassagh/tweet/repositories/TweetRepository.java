package com.github.manimovassagh.tweet.repositories;

import com.github.manimovassagh.tweet.entities.Tweet;
import com.github.manimovassagh.tweet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
    List<Tweet> findAllByUser(User user);
}
