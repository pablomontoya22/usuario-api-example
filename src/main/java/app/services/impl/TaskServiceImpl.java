package app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entities.Task;
import app.repositories.TaskRepository;
import app.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository repo;
	
	@Override
	public Task get(final Long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Task> getAll() {
		return (List<Task>) repo.findAll();
	}

	@Override
	public Task save(final Task task) {
		task.setId(null);
		return repo.save(task);
	}

	@Override
	public Boolean update(final Task task) {
		repo.save(task);
		return true;
	}

	@Override
	public Boolean deleteById(final Long id) {
		repo.deleteById(id);
		return true;
	}
}
