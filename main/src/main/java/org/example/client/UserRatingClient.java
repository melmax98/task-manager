package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@FeignClient(name = "userRatingService", url = "localhost:8081/api/user")
public interface UserRatingClient {

    @GetMapping("{id}/rating")
    Integer getUserRating(@PathVariable Integer id);

    @GetMapping("/rating/{usersId}")
    Map<Integer, Integer> getUsersRating(@PathVariable Set<Integer> usersId);
}
