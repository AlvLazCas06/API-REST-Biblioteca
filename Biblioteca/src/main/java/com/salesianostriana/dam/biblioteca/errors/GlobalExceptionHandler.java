package com.salesianostriana.dam.biblioteca.errors;

import com.salesianostriana.dam.biblioteca.exception.BibliotecaNotFoundException;
import com.salesianostriana.dam.biblioteca.exception.InvalidBibliotecaDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BibliotecaNotFoundException.class)
    public ProblemDetail handleBibliotecaNotFoundException(BibliotecaNotFoundException ex) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        result.setTitle("Biblioteca no encontrada");
        result.setType(URI.create("http://dam.salesianos-triana.com/biblioteca-not-found"));
        return result;
    }

    @ExceptionHandler(InvalidBibliotecaDataException.class)
    public ProblemDetail handleInvalidBibliotecaDataException(InvalidBibliotecaDataException ex) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        result.setTitle("Parámetros inválidos");
        result.setType(URI.create("http://dam.salesianos-triana.com/biblioteca-not-good"));
        return result;
    }

}
