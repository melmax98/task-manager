package org.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_rating")
public class UserRating {
    @Id
    private Integer userId;
    private Integer rating;

}
