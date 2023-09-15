package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.State;

public interface StateRepository extends CrudRepository<State, Long> {}