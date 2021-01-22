package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
   // User findByUsername(String username);

//    @Query("SELECT t.users FROM Task t WHERE t.id=:taskId")
//    List<User> findByTaskId(@Param("taskId") Integer taskId);
//
//    @Query("SELECT (COUNT (u) > 0) FROM User u WHERE u.email=:email")
//    boolean isEmailAlreadyExists(@Param("email") String email);

}
