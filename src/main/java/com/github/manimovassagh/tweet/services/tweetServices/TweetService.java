package com.github.manimovassagh.tweet.services.tweetServices;

import com.github.manimovassagh.tweet.DTO.*;
import com.github.manimovassagh.tweet.entities.Tweet;
import com.github.manimovassagh.tweet.entities.User;
import com.github.manimovassagh.tweet.repositories.TweetRepository;
import com.github.manimovassagh.tweet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TweetService {

    final TweetRepository tweetRepository;
    final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<Tweet> createTweet(TweetDto tweetDto, Principal principal) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetDto.getContent());
        tweet.setTimestamp(LocalDateTime.now());
        User user = userRepository.findByUsername(principal.getName());
        tweet.setUser(user);
        tweetRepository.save(tweet);
        return new ResponseEntity<>(tweetRepository.save(tweet), HttpStatus.CREATED);

    }


    public ResponseDTOForGeneral<List<Tweet>> getAllTweets() {

        ResponseDTOForGeneral<List<Tweet>> tweetResponse = new ResponseDTOForGeneral<>();
        List<Tweet> tweets = tweetRepository.findAll();
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        tweetResponse.setStatus(Status.SUCCESS);

        List<TweetResponseDTo> listTweets = tweets.stream()
                .map(tweet -> {
                    TweetResponseDTo dto = new TweetResponseDTo();
                    dto.setTweetId(tweet.getTweetId());
                    dto.setTimestamp(tweet.getTimestamp());
                    dto.setContent(tweet.getContent());
                    dto.setUsername(tweet.getUser().getUsername());
                    dto.setEmail(tweet.getUser().getEmail());

                    return dto;
                })
                .toList();
        tweetResponse.setTweets(listTweets);
        tweetResponse.setStatus(Status.SUCCESS);



        return tweetResponse;
    }


    public ResponseDTOForUser<List<Tweet>> getAllTweetsForUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        ResponseDTOForUser<List<Tweet>> tweetResponse = new ResponseDTOForUser<>();
        List<Tweet> tweets = tweetRepository.findAllByUser(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUsername(user.getUsername());
        tweetResponse.setStatus(Status.SUCCESS);

        List<TweetResponseDTo> listTweets = tweets.stream()
                .map(tweet -> {
                    TweetResponseDTo dto = new TweetResponseDTo();
                    dto.setTweetId(tweet.getTweetId());
                    dto.setTimestamp(tweet.getTimestamp());
                    dto.setContent(tweet.getContent());
                    dto.setEmail(user.getEmail());
                    dto.setUsername(user.getUsername());
                    return dto;
                })
                .toList();
        tweetResponse.setTweets(listTweets);
        tweetResponse.setStatus(Status.SUCCESS);
        tweetResponse.setUser(userResponseDTO);


        return tweetResponse;
    }


    public ResponseEntity<List<Tweet>>  getAllTweetsLazy(){

      return  new ResponseEntity<>(tweetRepository.findAll(),HttpStatus.OK) ;
    }


    public Map<String, List<Tweet>> getTweetsGroupedByUsername() {
        List<Tweet> allTweets = tweetRepository.findAll();

        // Group tweets by username
        Map<String, List<Tweet>> tweetsByUsername = new HashMap<>();
        for (Tweet tweet : allTweets) {
            String username = tweet.getUser().getUsername();
            tweetsByUsername.computeIfAbsent(username, k -> new ArrayList<>()).add(tweet);
        }

        return tweetsByUsername;
    }


}
