package net.emt.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class EtAuthException extends Exception {
	
	public EtAuthException(String message) {
		super(message);
	}

}
