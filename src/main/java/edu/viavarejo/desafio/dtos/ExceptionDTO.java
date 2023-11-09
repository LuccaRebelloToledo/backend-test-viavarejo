package edu.viavarejo.desafio.dtos;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(String mensagem, HttpStatus httpStatus) {

}
