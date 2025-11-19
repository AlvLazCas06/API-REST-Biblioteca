package com.salesianostriana.dam.biblioteca.dto;

import java.time.LocalDate;

public record CreateBibliotecaCmd(
        String cityName,
        String libraryName,
        LocalDate foundationDate,
        int numBooks,
        String description,
        String url
) {
}
