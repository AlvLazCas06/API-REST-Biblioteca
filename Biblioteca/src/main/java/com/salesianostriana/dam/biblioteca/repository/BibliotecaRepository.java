package com.salesianostriana.dam.biblioteca.repository;

import com.salesianostriana.dam.biblioteca.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository
        extends JpaRepository<Biblioteca, Long> {
}
