package edu.viavarejo.desafio.handlers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.viavarejo.desafio.dtos.ExceptionDTO;
import edu.viavarejo.desafio.exceptions.ProdutoException;
import edu.viavarejo.desafio.exceptions.TaxaJurosNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(TaxaJurosNotFoundException.class)
	public ResponseEntity<ExceptionDTO> taxaJurosNotFoundException(TaxaJurosNotFoundException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ExceptionDTO> iOException(IOException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Erro na solicitação dos dados de taxa de juros!",
				HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}

	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<ExceptionDTO> interruptedException(InterruptedException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Erro na solicitação dos dados de taxa de juros!",
				HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}

	@ExceptionHandler(ProdutoException.class)
	public ResponseEntity<ExceptionDTO> produtoException(ProdutoException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDTO> exceptionGeneral(Exception exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}

}
