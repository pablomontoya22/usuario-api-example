package app.controllers;

import java.util.List;
import java.util.Map;

import javax.swing.text.Utilities;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Users;
import app.helpers.Messages;
import app.helpers.Response;
import app.repositories.UsersRepository;

@RestController
@RequestMapping("user")
public class UsersController extends Utilities {

	@Autowired
	protected UsersRepository repo;

	@GetMapping(value = "/{id}")
	private Response get(@PathVariable("id") final Long id) {
		return new Response(repo.findById(id).get());
	}

	@GetMapping()
	private Response get() {
		return new Response((List<Users>) repo.findAll());
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	private Response save(@Valid @RequestBody final Users entity) {
		return new Response(repo.save(entity));
	}

	@PutMapping
	private Response update(@Valid @RequestBody final Users entity) {
		repo.save(entity);
		return new Response(HttpStatus.OK, Messages.UPDATED);
	}

	@DeleteMapping
	private Response delete(@RequestBody final Map<String, Long> data) {
		repo.deleteById(data.get("id"));
		return new Response(HttpStatus.OK, Messages.DELETED);
	}

}
