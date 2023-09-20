package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {}