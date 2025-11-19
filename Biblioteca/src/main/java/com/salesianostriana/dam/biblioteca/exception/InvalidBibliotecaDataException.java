package com.salesianostriana.dam.biblioteca.exception;

public class InvalidBibliotecaDataException extends RuntimeException {
    public InvalidBibliotecaDataException(String message) {
        super(message);
    }

    public InvalidBibliotecaDataException() {
        super("Los parámetros introducidos no son validos.");
    }
}
