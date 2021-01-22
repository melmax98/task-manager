package org.example.controller;

import org.example.entity.Comment;
import org.example.entity.Task;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/api/task")
public class TaskUserController {

    TaskService taskService;

    @Autowired
    public TaskUserController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/user/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@PathVariable Integer id, @Valid @RequestBody Task task, @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        if (file != null) {
            taskService.saveFile(task, file);
        }
        return taskService.createTask(id, task);
    }

    @PostMapping(value = "{taskId}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@PathVariable Integer taskId, @Valid @RequestBody Comment comment) {
       taskService.addComment(taskId, comment);
    }

    @DeleteMapping(value = "comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Integer commentId) {
        taskService.deleteComment(commentId);
    }
}
