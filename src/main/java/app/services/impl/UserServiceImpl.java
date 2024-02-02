package app.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.converters.UserConverter;
import app.dtos.UserDto;
import app.helpers.exceptions.AlreadyExistsException;
import app.models.User;
import app.repositories.UserRepository;
import app.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDto get(final UUID id) {
		return userConverter.toDto(userRepository.findById(id).get());
	}

	@Override
	public List<UserDto> getAll() {
		return ((List<User>) userRepository.findAll()).stream().map(a -> userConverter.toDto(a)).
			collect(Collectors.toList());
	}

	@Override
	public UserDto save(final UserDto obj) throws AlreadyExistsException {
		return userConverter.toDto(userRepository.saveUser(userConverter.convert(obj)));
	}

	@Override
	public Boolean update(final UserDto obj) {
		final User user = userConverter.convert(obj);
		user.setModified(new Date());
		userRepository.save(user);
		return true;
	}

	@Override
	public Boolean deleteById(final UUID id) {
		userRepository.deleteById(id);
		return true;
	}
}
