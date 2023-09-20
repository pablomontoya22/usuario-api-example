package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Task;
import app.helpers.Messages;
import app.helpers.Response;
import app.services.TaskService;

@RestController
@RequestMapping("task")
public class TaskController {

	@Autowired
	private TaskService service;

	/**
	 * <b>Retrieves an entity by its id.</b>
	 * 
	 * @param id numeric value
	 * @return The entity with the given id
	 */
	@GetMapping(value = "/{id}")
	public Response get(@PathVariable("id") final Long id) {
		return new Response(service.get(id));
	}

	/**
	 * <b>Retrieves all entities.</b>
	 *
	 * @return All the entities
	 */
	@GetMapping
	public Response getAll() {
		return new Response(service.getAll());
	}

	/**
	 * <b>Create a new record.</b>
	 *
	 * @param Task object
	 * @return Created record
	 */
	@PostMapping
	public Response save(@Valid @RequestBody final Task entity) {
		return new Response(service.save(entity));
	}

	/**
	 * <b>Update a record.</b>
	 *
	 * @param Task object
	 * @return Successful message
	 */
	@PutMapping
	public Response update(@Valid @RequestBody final Task entity) {
		service.update(entity);
		return new Response(HttpStatus.OK, Messages.UPDATED);
	}

	/**
	 * <b>Delete a record.</b>
	 *
	 * @param id numeric value
	 * @return Successful message
	 */
	@DeleteMapping(value = "/{id}")
	public Response delete(@PathVariable("id") final Long id) {
		service.deleteById(id);
		return new Response(HttpStatus.OK, Messages.DELETED);
	}

}
