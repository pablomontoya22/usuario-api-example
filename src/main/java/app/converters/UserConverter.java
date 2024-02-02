package app.converters;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import app.dtos.UserDto;
import app.handlers.JWTHandler;
import app.models.Phone;
import app.models.User;

@Component
public class UserConverter implements Converter<UserDto, User> {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PhoneConverter phoneConverter;
	@Autowired
	private JWTHandler jwtHandler;

	@Override
	public User convert(final UserDto source) {
		if (source == null)
			return null;
		if (source.getId() == null) {
			source.setId(UUID.randomUUID());
			return updateEntityProperties(User.builder().isActive(true).build(), source);
		} else {
			return updateEntityProperties(getIfExists(source.getEmail()), source);
		}
	}

	public UserDto toDto(final User entity) {
		if (entity == null)
			return null;
		return updateDtoProperties(entity);
	}

	private User getIfExists(final String email) {
		return manager.createNamedQuery(User.FIND_BY_EMAIL, User.class).setParameter(User.PARAM_EMAIL, email).
			getResultList().stream().findFirst().orElse(User.builder().isActive(true).build());
	}

	private User updateEntityProperties(final User entity, final UserDto dto) {
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		if (entity.getToken() == null) {
			entity.setToken(jwtHandler.create(entity));
		} else {
			final DecodedJWT jwt = jwtHandler.decode(entity.getToken());
			if (jwt.getExpiresAt().before(new Date())) {
				entity.setToken(jwtHandler.create(entity));
			}
		}
		if (entity.getPhones() == null) {
			entity.setPhones(new HashSet<>());
		}
		entity.getPhones().clear();
		entity.getPhones().addAll(dto.getPhones().stream().map(a -> {
			final Phone p = phoneConverter.convert(a);
			p.setUser(entity);
			return p;
		}).collect(Collectors.toSet()));
		return entity;
	}

	private UserDto updateDtoProperties(final User entity) {
		final UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setCreated(entity.getCreated());
		dto.setModified(entity.getModified());
		dto.setLastLogin(entity.getLastLogin());
		dto.setActive(entity.isActive());
		dto.setToken(entity.getToken());
		dto.setPhones(entity.getPhones().stream().map(a -> phoneConverter.toDto(a)).collect(Collectors.toList()));
		return dto;
	}
}
