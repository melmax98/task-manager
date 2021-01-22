package org.example.repository;

import org.example.entity.Task;
import org.example.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM task INNER JOIN users_comments ON task.id=users_comments.task_id WHERE task.id = ?", nativeQuery = true)
    Task findByComment(Integer commentId);

    @Query(value = "SELECT * FROM task INNER JOIN user_role ON task.user_id=user_role.user_id WHERE roles=?", nativeQuery = true)
    List<Task> findByUnit(String unit);
}
