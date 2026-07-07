package com.sivaguru.taskapi.utils;

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

    return repo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(entityName + " not found with id: " + id));
  }


}
