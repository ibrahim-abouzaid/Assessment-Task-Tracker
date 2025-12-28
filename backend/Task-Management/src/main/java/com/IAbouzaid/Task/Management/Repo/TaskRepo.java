package com.IAbouzaid.Task.Management.Repo;

import com.IAbouzaid.Task.Management.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByUserId (Long userID);
}
