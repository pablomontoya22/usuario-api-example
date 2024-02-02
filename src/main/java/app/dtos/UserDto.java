package app.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private UUID id;

	@NotNull(message = "Se requiere el atributo 'name'.")
	@Size(max = 100, message = "El name solo puede ser hasta 100 caracteres.")
	private String name;

	@NotNull(message = "Se requiere el atributo 'email'.")
	@Size(max = 50, message = "El email solo puede ser hasta 50 caracteres.")
	@Email(message = "No es válido", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String email;

	@NotNull(message = "Se requiere el atributo 'password'.")
	@Size(max = 50, message = "El name solo puede ser hasta 50 caracteres.")
	@Pattern(message = "El password no es válido", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,}$")
	private String password;

	@Valid
	@NotNull(message = "Se requiere el atributo 'phones'.")
	private List<PhoneDto> phones;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modified;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLogin;

	private boolean isActive;
	private String token;
}
