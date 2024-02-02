package app.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
	@NotNull(message = "{email.notempty} Se requiere el atributo 'number'.")
	@Size(max = 7, message = "${validatedValue} solo puede ser hasta {max} caracteres.")
	private String number;

	@JsonProperty("citycode")
	@NotNull(message = "Se requiere el atributo 'citycode'.")
	@Size(max = 5, message = "El citycode solo puede ser hasta {max} caracteres.")
	private String cityCode;

	@JsonProperty("contrycode")
	@NotNull(message = "Se requiere el atributo 'contrycode'.")
	@Size(max = 5, message = "El contrycode solo puede ser hasta {max} caracteres.")
	private String countryCode;
}
