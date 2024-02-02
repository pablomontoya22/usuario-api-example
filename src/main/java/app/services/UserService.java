package app.services;

import java.util.List;
import java.util.UUID;

import app.dtos.UserDto;
import app.helpers.exceptions.AlreadyExistsException;

public interface UserService {
	UserDto get(final UUID id);
	List<UserDto> getAll();
	UserDto save(final UserDto obj) throws AlreadyExistsException;
	Boolean update(final UserDto obj);
	Boolean deleteById(final UUID id);
}
