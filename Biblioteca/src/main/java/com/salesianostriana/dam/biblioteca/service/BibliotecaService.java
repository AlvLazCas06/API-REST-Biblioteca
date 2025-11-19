package com.salesianostriana.dam.biblioteca.service;

import com.salesianostriana.dam.biblioteca.dto.BibliotecaResponse;
import com.salesianostriana.dam.biblioteca.dto.CreateBibliotecaCmd;
import com.salesianostriana.dam.biblioteca.exception.BibliotecaNotFoundException;
import com.salesianostriana.dam.biblioteca.exception.InvalidBibliotecaDataException;
import com.salesianostriana.dam.biblioteca.mapper.BibliotecaMapper;
import com.salesianostriana.dam.biblioteca.model.Biblioteca;
import com.salesianostriana.dam.biblioteca.repository.BibliotecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final BibliotecaRepository repository;
    private final BibliotecaMapper mapper;

    public BibliotecaResponse save(CreateBibliotecaCmd cmd) {
        Biblioteca biblioteca = validate(cmd);
        return mapper.toDto(repository.save(biblioteca));
    }

    public BibliotecaResponse findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new BibliotecaNotFoundException(id)));
    }

    public List<BibliotecaResponse> findAll() {
        List<Biblioteca> results = repository.findAll();
        if (results.isEmpty()) {
            throw new BibliotecaNotFoundException("No existen bibliotecas.");
        }
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public BibliotecaResponse edit(CreateBibliotecaCmd cmd, Long id) {
        Biblioteca biblioteca = validate(cmd);
        if (repository.findById(id).isEmpty()) {
            throw new BibliotecaNotFoundException(id);
        } else {
            biblioteca.setId(id);
            return mapper.toDto(repository.save(biblioteca));
        }
    }

    public void delete(Long id) {
        Biblioteca biblioteca = repository.findById(id)
                .orElseThrow(() -> new BibliotecaNotFoundException(id));
        repository.delete(biblioteca);
    }

    private Biblioteca validate(CreateBibliotecaCmd cmd) {
        if (!StringUtils.hasText(cmd.cityName())) {
            throw new InvalidBibliotecaDataException();
        } else if (!StringUtils.hasText(cmd.libraryName())) {
            throw new InvalidBibliotecaDataException();
        } else if (cmd.foundationDate() == null || cmd.foundationDate().isAfter(LocalDate.now())) {
            throw new InvalidBibliotecaDataException();
        } else if (cmd.numBooks() < 0) {
            throw new InvalidBibliotecaDataException();
        } else if (!StringUtils.hasText(cmd.description())) {
            throw new InvalidBibliotecaDataException();
        } else {
            if (!StringUtils.hasText(cmd.url())) {
                throw new InvalidBibliotecaDataException();
            }
        }
        return mapper.toEntity(cmd);
    }

}
