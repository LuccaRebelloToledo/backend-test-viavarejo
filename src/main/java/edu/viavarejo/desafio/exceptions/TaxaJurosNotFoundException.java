package edu.viavarejo.desafio.exceptions;

public class TaxaJurosNotFoundException extends Exception {

	private static final long serialVersionUID = 8204107100818029559L;
	
    public TaxaJurosNotFoundException(String message) {
        super(message);
    }
    
    public TaxaJurosNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
