package com.salesianostriana.dam.biblioteca.dto;

import java.time.LocalDate;

public record BibliotecaResponse(
        Long id,
        String cityName,
        String libraryName,
        LocalDate foundationDate,
        int numBooks,
        String description,
        String url
) { }
