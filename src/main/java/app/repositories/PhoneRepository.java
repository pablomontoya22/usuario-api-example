package app.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import app.models.Phone;

public interface PhoneRepository extends CrudRepository<Phone, UUID> {}