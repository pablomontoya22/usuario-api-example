package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dtos.UserDto;
import app.helpers.Messages;
import app.helpers.Response;
import app.helpers.exceptions.AlreadyExistsException;
import app.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService service;

	/**
	 * <b>Retrieves an entity by its id.</b>
	 * 
	 * @param id numeric value
	 * @return The entity with the given id
	 */
	@GetMapping
	public Response get(@RequestBody final UserDto entity) {
		return new Response(service.get(entity.getId()));
	}

	/**
	 * <b>Retrieves all entities.</b>
	 *
	 * @return All the entities
	 */
	@GetMapping("/all")
	public Response getAll() {
		return new Response(service.getAll());
	}

	/**
	 * <b>Create a new record.</b>
	 *
	 * @param UserDto object
	 * @return Created record
	 * @throws AlreadyExistsException 
	 */
	@PostMapping
	public Response save(@Valid @RequestBody final UserDto entity) throws AlreadyExistsException {
		return new Response(service.save(entity));
	}

	/**
	 * <b>Update a record.</b>
	 *
	 * @param UserDto object
	 * @return Successful message
	 */
	@PutMapping
	public Response update(@Valid @RequestBody final UserDto entity) {
		service.update(entity);
		return new Response(HttpStatus.OK, Messages.UPDATED);
	}

	/**
	 * <b>Delete a record.</b>
	 *
	 * @param id numeric value
	 * @return Successful message
	 */
	@DeleteMapping
	public Response delete(@RequestBody final UserDto entity) {
		service.deleteById(entity.getId());
		return new Response(HttpStatus.OK, Messages.DELETED);
	}
}
