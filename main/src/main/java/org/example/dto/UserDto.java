package org.example.dto;

import lombok.Data;
import org.example.entity.Unit;

import java.util.Set;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private Set<Unit> roles;
    private Integer rating;
}
