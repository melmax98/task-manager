package org.example.service;

import org.example.entity.UserRating;
import org.example.repository.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRatingService {

    UserRatingRepository userRatingRepository;

    @Autowired
    UserRatingService(UserRatingRepository userRatingRepository) {
        this.userRatingRepository = userRatingRepository;
    }

    public Integer getUserRating(Integer id) {
        UserRating one = userRatingRepository.getOne(id);
        System.out.println(one);
        return one.getRating();
    }

    public UserRating setUserRating(Integer id, Integer rating) {
        UserRating userRating = new UserRating();
        userRating.setUserId(id);
        userRating.setRating(rating);
        return userRatingRepository.save(userRating);
    }
}
