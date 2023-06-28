package com.alibou.keycloak.controllers;

import com.alibou.keycloak.DTO.TweetDto;
import com.alibou.keycloak.entities.Tweet;
import com.alibou.keycloak.entities.User;
import com.alibou.keycloak.repositories.TweetRepository;
import com.alibou.keycloak.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TweetController {


    @Autowired
    TweetRepository tweetRepository;
    UserRepository userRepository;

    @Autowired
    public TweetController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/test")
    @PreAuthorize("hasRole('client_user')")
    public String sayHi(Principal principal) {
        return "User with the userName ###### " + principal.getName() + " ###### said Hi from tweet controller";
    }


    @PostMapping("/tweet")
    @PreAuthorize("hasRole('client_user')")
    public Tweet createTweet(@RequestBody TweetDto tweetDto, Principal principal) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetDto.getContent());
        tweet.setTimestamp(LocalDateTime.now());
        User user = userRepository.findByUsername(principal.getName());
        tweet.setUser(user);
        tweetRepository.save(tweet);
        return tweet;
    }


}
