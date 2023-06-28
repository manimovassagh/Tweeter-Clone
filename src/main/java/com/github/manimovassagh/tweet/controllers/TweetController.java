package com.github.manimovassagh.tweet.controllers;

import com.github.manimovassagh.tweet.DTO.ResponseDTO;
import com.github.manimovassagh.tweet.DTO.Status;
import com.github.manimovassagh.tweet.DTO.TweetDto;
import com.github.manimovassagh.tweet.entities.Tweet;
import com.github.manimovassagh.tweet.entities.User;
import com.github.manimovassagh.tweet.repositories.TweetRepository;
import com.github.manimovassagh.tweet.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TweetController {


    TweetRepository tweetRepository;
    UserRepository userRepository;

    public TweetController(UserRepository userRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }


    @GetMapping("/test")
    @PreAuthorize("hasRole('client_user')")
    public String sayHi(Principal principal) {
        return "User with the userName ###### " + principal.getName() + " ###### said Hi from tweet controller";
    }


    @PostMapping("/tweet")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<Tweet> createTweet(@RequestBody TweetDto tweetDto, Principal principal) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetDto.getContent());
        tweet.setTimestamp(LocalDateTime.now());
        User user = userRepository.findByUsername(principal.getName());
        tweet.setUser(user);
        tweetRepository.save(tweet);
        return new ResponseEntity<>(tweetRepository.save(tweet), HttpStatus.CREATED);

    }


    @GetMapping("/tweet")
    @PreAuthorize("hasRole('client_user')")
    public ResponseDTO<List<Tweet>> getAllTweets() {
        ResponseDTO<List<Tweet>> tweetResponse = new ResponseDTO<>();
        tweetResponse.setResponse(tweetRepository.findAll());
        tweetResponse.setStatus(Status.SUCCESS);
        return tweetResponse;
    }

    @GetMapping("/tweet/user")
    @PreAuthorize("hasRole('client_user')")
    public ResponseDTO<List<Tweet>> getAllTweetsForUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        ResponseDTO<List<Tweet>> tweetResponse = new ResponseDTO<>();
        List<Tweet> tweets = tweetRepository.findAllByUser(user);
        tweetResponse.setStatus(Status.SUCCESS);
        tweetResponse.setResponse(tweets);
        tweetResponse.setResponse(tweets);
        tweetResponse.setStatus(Status.SUCCESS);
        return tweetResponse;
    }


}
