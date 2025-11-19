package com.salesianostriana.dam.biblioteca.exception;

public class BibliotecaNotFoundException extends RuntimeException {
    public BibliotecaNotFoundException(String message) {
        super(message);
    }

    public BibliotecaNotFoundException(Long id) {
        super("La biblioteca con el id: %d, no existe".formatted(id));
    }
}
