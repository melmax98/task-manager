package org.example.repository;

import org.example.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

//    @Query("SELECT u.tasks FROM User u WHERE u.id=:userId")
//    List<Task> findByUserId(@Param("userId") Integer userId);
//
//    @Query("SELECT (COUNT (t) > 0) AS boolean FROM User u " +
//            "INNER JOIN u.tasks t WHERE t.id =:id AND u.id=:userId ")
//    Boolean isTaskContainingInUser(@Param("id") Integer id, @Param("userId") Integer userId);

}
