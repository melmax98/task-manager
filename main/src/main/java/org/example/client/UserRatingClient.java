package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userRatingService",url = "localhost:8081/api/user")
public interface UserRatingClient {

    @GetMapping("{id}/rating")
    Integer getUserRating(@PathVariable Integer id);
}
