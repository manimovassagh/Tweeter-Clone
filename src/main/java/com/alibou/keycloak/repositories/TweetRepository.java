package com.alibou.keycloak.repositories;

import com.alibou.keycloak.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
}
