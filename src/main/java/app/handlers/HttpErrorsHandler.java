package app.handlers;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import app.helpers.Messages;
import app.helpers.Response;
import app.helpers.exceptions.AlreadyExistsException;

@ControllerAdvice
public class HttpErrorsHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Object handleValidationExceptions(final MethodArgumentNotValidException e) {
		final String errors = e.getBindingResult().getAllErrors().stream().
		    map(a -> ((FieldError) a).getField() + ": " + a.getDefaultMessage()).
		    collect(Collectors.joining(", "));
	    return new Response(HttpStatus.BAD_REQUEST, errors);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
	@ResponseBody
	public Object handleInternalNotFoundExceptions(final Exception e) {
	    return new Response(HttpStatus.NOT_FOUND, Messages.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public Object handleDataIntegrityViolationExceptions(final DataIntegrityViolationException e) {
	    return new Response(HttpStatus.CONFLICT, Messages.CONFLICT);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(AlreadyExistsException.class)
	@ResponseBody
	public Object handleEmailAlreadyExistsExceptions(final AlreadyExistsException e) {
	    return new Response(HttpStatus.CONFLICT, e.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleInternalServerErrorExceptions(final Exception e) {
	    return new Response(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
	}
}
