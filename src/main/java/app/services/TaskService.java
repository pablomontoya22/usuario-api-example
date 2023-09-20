package app.services;

import java.util.List;

import app.entities.Task;

public interface TaskService {
	Task get(final Long id);
	List<Task> getAll();
	Task save(final Task task);
	Boolean update(final Task task);
	Boolean deleteById(final Long id);
}
