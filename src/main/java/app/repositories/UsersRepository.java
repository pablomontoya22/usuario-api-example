package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {}