package org.example.controller;

import org.example.entity.Task;
import org.example.entity.Unit;
import org.example.entity.User;
import org.example.repository.TaskRepository;
import org.example.repository.UserRepository;
import org.example.service.TaskService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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

    @GetMapping
    public @ResponseBody List<Task> getAll() {
//        Task task = new Task();
//        User user1 = userRepository.findById(1).get();
//        User user2 = userRepository.findById(2).get();
//
//        task.setIsCompleted(false);
//        task.setUsers(Arrays.asList(user1, user2));
//        task.setFilename("1.txt");
//        task.setTopic("the topic ");
//        task.setId(1);
//        task.setCreateDate(LocalDate.now());
//        task.setDescription("The desc ");
//        task.setCreatedBy("melmax98");
//
//        taskRepository.save(task);
        return taskService.findAll();
    }

    @GetMapping("/filter/{unit}/{sort}")
    public @ResponseBody Iterable<Task> getWithFilter(@PathVariable String unit, @PathVariable(required = false) Integer sort) {
        List<Task> tasks;
        if ("all".equals(unit)) {
            tasks = taskService.findAll();
        }
        else {
            tasks = taskService.getWithfilter(unit);
        }
        List<Task> result;
        if (sort == null) {
            sort = 0;
        }
        if (sort == 0) {
            result = tasks.stream().sorted(Comparator.comparing(Task::getCreateDate)).collect(Collectors.toList());
        } else {
            result = tasks.stream().sorted(Comparator.comparing(Task::getCreateDate).reversed()).collect(Collectors.toList());
        }

        return result;
    }


}
