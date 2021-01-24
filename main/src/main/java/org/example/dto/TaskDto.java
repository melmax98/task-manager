package org.example.dto;

import lombok.Data;
import org.example.entity.Comment;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDto {
    private Integer id;
    private String description;
    private String topic;
    private Boolean isCompleted;
    private List<UserDto> users = new ArrayList<>();
    private File file;
    private UserDto createdBy;
    private LocalDate createDate;
    private List<Comment> comments;
}
