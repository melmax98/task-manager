package org.example.service;

import org.example.client.UserRatingClient;
import org.example.dto.TaskDto;
import org.example.dto.UserDto;
import org.example.entity.Comment;
import org.example.entity.Task;
import org.example.entity.User;
import org.example.repository.CommentRepository;
import org.example.repository.TaskRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    private UserRatingClient userRatingClient;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, CommentRepository commentRepository, UserRatingClient userRatingClient) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.userRatingClient = userRatingClient;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void saveFile(Task task, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            task.setFilename(resultFilename);
        }
    }

    public Task updateTask(Integer id, Task updated) {
        Task task = taskRepository.findById(id).get();
        if (!updated.getUsers().isEmpty()) {
            task.setUsers(updated.getUsers());
        }
        if (updated.getIsCompleted() != null) {
            task.setIsCompleted(updated.getIsCompleted());
        }
        return taskRepository.save(task);
    }

    public Task createTask(Integer userId, Task task) {
        User user = userRepository.getOne(userId);
        task.setCreatedBy(user);
        task.setCreateDate(LocalDate.now());
        task.setIsCompleted(false);
        return taskRepository.save(task);
    }

    public void addComment(Integer taskId, Comment comment) {
        Task task = taskRepository.getOne(taskId);
        comment.setUser(comment.getUser());
        comment.setText(comment.getText());
        task.getComments().add(comment);
        commentRepository.save(comment);
        taskRepository.save(task);
    }

    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.getOne(commentId);
        Task task = taskRepository.findByComment(commentId);
        task.getComments().remove(comment);
        taskRepository.save(task);
        commentRepository.delete(comment);
    }

    public List<Task> getWithfilter(String unit) {
        return taskRepository.findByUnit(unit);
    }

    public String getTaskDetails(Integer id) {
        Task task = taskRepository.getOne(id);
        return "Comments: " + task.getComments() + "\n"
                + "Attachment filename: " + task.getFilename();
    }

    public List<TaskDto> findAllTaskDto() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> tasksDto = new ArrayList<>();
        for (Task task: tasks) {
            List<User> users = task.getUsers();
            List<UserDto> usersDto = new ArrayList<>();

            TaskDto taskDto = new TaskDto();
            taskDto.setComments(task.getComments());
            taskDto.setDescription(task.getDescription());
            taskDto.setFile(new File(uploadPath + "/" + task.getFilename()));
            taskDto.setId(task.getId());

            UserDto temp = new UserDto();
            temp.setRating(userRatingClient.getUserRating(task.getCreatedBy().getId()));
            temp.setUsername(task.getCreatedBy().getUsername());
            temp.setRoles(task.getCreatedBy().getRoles());
            temp.setId(task.getCreatedBy().getId());
            taskDto.setCreatedBy(temp);

            taskDto.setCreateDate(task.getCreateDate());
            taskDto.setTopic(task.getTopic());
            taskDto.setIsCompleted(task.getIsCompleted());

            for(User user: users) {
                UserDto userDto = new UserDto();
                userDto.setUsername(user.getUsername());
                userDto.setRoles(user.getRoles());
                userDto.setId(user.getId());
                userDto.setRating(userRatingClient.getUserRating(user.getId()));
                usersDto.add(userDto);
            }
            taskDto.setUsers(usersDto);
            tasksDto.add(taskDto);
        }
        return tasksDto;
    }
}
