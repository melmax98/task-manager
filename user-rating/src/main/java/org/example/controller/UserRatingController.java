package org.example.controller;

import org.example.entity.UserRating;
import org.example.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RequestMapping("/api/user")
@Controller
public class UserRatingController {

    UserRatingService userRatingService;

    @Autowired
    UserRatingController(UserRatingService userRatingService) {
        this.userRatingService = userRatingService;
    }

    @GetMapping("{id}/rating")
    public @ResponseBody
    Integer getUserRating(@PathVariable Integer id) {
        return userRatingService.getUserRating(id);
    }

    @PostMapping(value = "{id}/rating")
    public @ResponseBody
    UserRating setUserRating(@PathVariable Integer id, @RequestParam Integer rating) {
        return userRatingService.setUserRating(id, rating);
    }

    @GetMapping("/rating/{usersId}")
    public @ResponseBody
    Map<Integer, Integer> getUsersRating(@PathVariable Set<Integer> usersId) {
        return userRatingService.getUserRating(usersId);
    }

}
