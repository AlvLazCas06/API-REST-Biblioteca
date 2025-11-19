package com.salesianostriana.dam.biblioteca.mapper;

import com.salesianostriana.dam.biblioteca.dto.BibliotecaResponse;
import com.salesianostriana.dam.biblioteca.dto.CreateBibliotecaCmd;
import com.salesianostriana.dam.biblioteca.model.Biblioteca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BibliotecaMapper {

    BibliotecaResponse toDto(Biblioteca biblioteca);

    Biblioteca toEntity(CreateBibliotecaCmd cmd);

}
