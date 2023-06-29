package com.github.manimovassagh.tweet.controllers;

import com.github.manimovassagh.tweet.DTO.ResponseDTOForGeneral;
import com.github.manimovassagh.tweet.DTO.ResponseDTOForUser;
import com.github.manimovassagh.tweet.DTO.TweetDto;
import com.github.manimovassagh.tweet.entities.Tweet;
import com.github.manimovassagh.tweet.repositories.TweetRepository;
import com.github.manimovassagh.tweet.repositories.UserRepository;
import com.github.manimovassagh.tweet.services.tweetServices.TweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TweetController {


    TweetRepository tweetRepository;
    UserRepository userRepository;
    TweetService tweetService;

    public TweetController(UserRepository userRepository, TweetRepository tweetRepository, TweetService tweetService) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.tweetService = tweetService;
    }


    @GetMapping("/test")
    @PreAuthorize("hasRole('client_user')")
    public String sayHi(Principal principal) {
        return "User with the userName ###### " + principal.getName() + " ###### said Hi from tweet controller";
    }


    @PostMapping("/tweet")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<Tweet> createTweet(@RequestBody TweetDto tweetDto, Principal principal) {
        return tweetService.createTweet(tweetDto, principal);

    }


    @GetMapping("/tweet")
    @PreAuthorize("hasRole('client_user')")
    public ResponseDTOForGeneral<List<Tweet>> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/tweet/user")
    @PreAuthorize("hasRole('client_user')")
    public ResponseDTOForUser<List<Tweet>> getAllTweetsForUser(Principal principal) {
        return tweetService.getAllTweetsForUser(principal);
    }

    @GetMapping("/tweet/userLazy")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<List<Tweet>> getAllTweetsLazy() {
        return tweetService.getAllTweetsLazy();
    }


    @GetMapping("/tweet/group")
    @PreAuthorize("hasRole('client_user')")
    public Map<String, List<Tweet>> getTweetsGroupedByUsername() {
        return tweetService.getTweetsGroupedByUsername();
    }

}
