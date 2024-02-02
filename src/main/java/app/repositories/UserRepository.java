package app.repositories;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;

import app.helpers.exceptions.AlreadyExistsException;
import app.models.User;

public interface UserRepository extends CrudRepository<User, UUID> {
	public default User saveUser(User entity) throws AlreadyExistsException {
		try {
			return this.save(entity);
		} catch (final DataIntegrityViolationException e) {
			throw new AlreadyExistsException("El correo ya está registrado.");
		}
	}
}