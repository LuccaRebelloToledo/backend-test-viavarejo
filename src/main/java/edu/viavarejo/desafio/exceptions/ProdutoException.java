package edu.viavarejo.desafio.exceptions;

public class ProdutoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoException(String message) {
        super(message);
    }
    
    public ProdutoException(String message, Throwable cause) {
        super(message, cause);
    }

}
