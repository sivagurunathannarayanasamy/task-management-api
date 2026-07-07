package com.sivaguru.taskapi.repository;

import com.sivaguru.taskapi.model.Task;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
