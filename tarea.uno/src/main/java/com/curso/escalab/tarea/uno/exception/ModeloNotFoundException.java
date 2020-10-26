package com.curso.escalab.tarea.uno.exception;

public class ModeloNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
    public ModeloNotFoundException() {
        super();
    }

    public ModeloNotFoundException(String message) {
        super(message);
    }
    
    public ModeloNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	

}
