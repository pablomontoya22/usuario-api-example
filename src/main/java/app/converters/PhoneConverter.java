package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.dtos.PhoneDto;
import app.models.Phone;

@Component
public class PhoneConverter implements Converter<PhoneDto, Phone> {
	@Override
	public Phone convert(final PhoneDto dto) {
		if (dto == null)
			return null;
		return updateEntityProperties(getIfExists(dto), dto);
	}

	public PhoneDto toDto(final Phone entity) {
		if (entity == null)
			return null;
		return updateDtoProperties(entity);
	}

	private Phone getIfExists(final PhoneDto dto) {
		return new Phone();
	}

	private Phone updateEntityProperties(final Phone entity, final PhoneDto dto) {
		entity.setNumber(dto.getNumber());
		entity.setCityCode(dto.getCityCode());
		entity.setCountryCode(dto.getCountryCode());
		return entity;
	}

	private PhoneDto updateDtoProperties(final Phone entity) {
		final PhoneDto dto = new PhoneDto();
		dto.setNumber(entity.getNumber());
		dto.setCityCode(entity.getCityCode());
		dto.setCountryCode(entity.getCountryCode());
		return dto;
	}
}
