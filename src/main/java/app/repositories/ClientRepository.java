package app.repositories;

import org.springframework.data.repository.CrudRepository;

import app.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
