package com.sivaguru.taskapi.utils;

import com.sivaguru.taskapi.exceptions.TaskNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryUtils {

  public static <T, ID> T findByIdOrThrow(
      JpaRepository<T, ID> repo,
      ID id,
      String entityName) {
    if (id == null) {
      throw new IllegalArgumentException(entityName + " id cannot be null");
    }

    // TODO: exception type is Task-specific but this util is generic —
    // revisit when a second entity is added

    return repo.findById(id)
        .orElseThrow(() -> new TaskNotFoundException(entityName + " not found with id: " + id));
  }


}
