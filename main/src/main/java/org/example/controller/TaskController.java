package org.example.controller;

import org.example.dto.TaskDto;
import org.example.entity.Task;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/task")
public class TaskController {
    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @Valid @RequestBody Task task) {
        taskService.updateTask(id, task);
    }

    @GetMapping("/filter/{unit}/{sort}")
    public @ResponseBody
    Iterable<Task> getWithFilter(@PathVariable String unit, @PathVariable(required = false) Integer sort) {
        List<Task> tasks;
        if ("all".equals(unit)) {
            tasks = taskService.findAll();
        } else {
            tasks = taskService.getWithfilter(unit);
        }
        List<Task> result = null;
        if (sort == null) {
            sort = 0;
        }
        if (sort == 0) {
            result = tasks.stream().sorted(Comparator.comparing(Task::getCreateDate)).collect(Collectors.toList());
        } else if (sort == 1) {
            result = tasks.stream().sorted(Comparator.comparing(Task::getCreateDate).reversed()).collect(Collectors.toList());
        }
        return result;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String getTaksDetails(@PathVariable Integer id) {
        return taskService.getTaskDetails(id);
    }

    @GetMapping
    public @ResponseBody
    Iterable<TaskDto> getAllTasks() {
        return taskService.findAllTaskDto();
    }


}
