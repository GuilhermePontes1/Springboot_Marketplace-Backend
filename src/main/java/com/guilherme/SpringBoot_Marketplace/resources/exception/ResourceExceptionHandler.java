package com.guilherme.SpringBoot_Marketplace.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.guilherme.SpringBoot_Marketplace.services.exception.ObjectNotFoundException;

// classe auxiliar que vai interceptar as exceções 
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) // para indicar que é uma exceção desse tipo de exceção
	public ResponseEntity<StandardError> objectNotFoud(ObjectNotFoundException e, HttpServletRequest request) {
		// em cima é padrão da anotação ControllerAdvice
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		// em cima estamos instanciando o erro que irá retornar o notFound no caso 404,
		// o id que procura além do
		// pacote e o tempo de execução

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
