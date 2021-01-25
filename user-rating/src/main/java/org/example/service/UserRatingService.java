package org.example.service;

import org.example.entity.UserRating;
import org.example.repository.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UserRatingService {

    UserRatingRepository userRatingRepository;

    @Autowired
    UserRatingService(UserRatingRepository userRatingRepository) {
        this.userRatingRepository = userRatingRepository;
    }

    public Integer getUserRating(Integer id) {
        UserRating one = userRatingRepository.getOne(id);
        return one.getRating();
    }

    public UserRating setUserRating(Integer id, Integer rating) {
        UserRating userRating = new UserRating();
        userRating.setUserId(id);
        userRating.setRating(rating);
        return userRatingRepository.save(userRating);
    }

    public Map<Integer, Integer> getUserRating(Set<Integer> usersId) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer id: usersId) {
            UserRating one = userRatingRepository.getOne(id);
            result.put(one.getUserId(), one.getRating());
        }

        return result;
    }
}
