package app.helpers;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Response {
	private final short code;
	private final Object data;
	private final String status;
	private final String message;
	private final long timestamp;

	public Response(final HttpStatus code, final Object message) {
		this.code = (short) code.value();
		this.data = null;
		this.message = message instanceof Messages
			? ((Messages) message).val() : message.toString();
		status =  code.value() >= 300 ? "Error" : "OK";
		timestamp = new Date().getTime();
	}

	public Response(final Object data) {
		code = (short) HttpStatus.OK.value();
		this.data = data;
		message = "";
		status = "OK";
		timestamp = new Date().getTime();
	}
}