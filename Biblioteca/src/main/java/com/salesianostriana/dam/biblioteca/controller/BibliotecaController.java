package com.salesianostriana.dam.biblioteca.controller;

import com.salesianostriana.dam.biblioteca.dto.BibliotecaResponse;
import com.salesianostriana.dam.biblioteca.dto.CreateBibliotecaCmd;
import com.salesianostriana.dam.biblioteca.service.BibliotecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@Tag(name = "Biblioteca", description = "Controlador de bibliotecas")
public class BibliotecaController {

    private final BibliotecaService service;

    @Operation(summary = "Obtener todas la bibliotecas de la base de datos")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "La lista de bibliotecas se cargado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BibliotecaResponse.class)),
                            examples = @ExampleObject(value = """
                                    [
                                        {
                                            "id": 1,
                                            "cityName": "Madrid",
                                            "libraryName": "Biblioteca Nacional de España",
                                            "foundationDate": "1712-12-29",
                                            "numBooks": 30000000,
                                            "description": "La Biblioteca Nacional de España es un centro bibliográfico español que tiene por objetivo reunir, catalogar y conservar fondos bibliográficos, así como promover la investigación y difundir la información bibliográfica.",
                                            "url": "https://www.bne.es"
                                        }
                                    ]
                                    """

                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No hay ninguna biblioteca dentro de la base de datos"
            )
    })
    @GetMapping
    public List<BibliotecaResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Guardar biblioteca en la base de datos")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "La biblioteca se ha guardado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BibliotecaResponse.class),
                            examples = @ExampleObject("""
                                    {
                                            "id": 1,
                                            "cityName": "Madrid",
                                            "libraryName": "Biblioteca Nacional de España",
                                            "foundationDate": "1712-12-29",
                                            "numBooks": 30000000,
                                            "description": "La Biblioteca Nacional de España es un centro bibliográfico español que tiene por objetivo reunir, catalogar y conservar fondos bibliográficos, así como promover la investigación y difundir la información bibliográfica.",
                                            "url": "https://www.bne.es"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los parámetros introducidos son inválidos."
            )
    })
    @PostMapping
    public ResponseEntity<BibliotecaResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para poder introducir una biblioteca en la base de datos",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateBibliotecaCmd.class),
                            examples = @ExampleObject("""
                                    {
                                            "cityName": "Madrid",
                                            "libraryName": "Biblioteca Nacional de España",
                                            "foundationDate": "1712-12-29",
                                            "numBooks": 30000000,
                                            "description": "La Biblioteca Nacional de España es un centro bibliográfico español que tiene por objetivo reunir, catalogar y conservar fondos bibliográficos, así como promover la investigación y difundir la información bibliográfica.",
                                            "url": "https://www.bne.es"
                                    }
                                    """)
                    )
            )
            @RequestBody CreateBibliotecaCmd cmd
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(cmd));
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<BibliotecaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Edita bibliotecas existentes en la base de datos")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "El monumento ha sido editado correctamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BibliotecaResponse.class),
                            examples = @ExampleObject("""
                                    {
                                            "id": 1,
                                            "cityName": "Madrid",
                                            "libraryName": "Biblioteca Nacional de España",
                                            "foundationDate": "1712-12-29",
                                            "numBooks": 30000000,
                                            "description": "La Biblioteca Nacional de España es un centro bibliográfico español que tiene por objetivo reunir, catalogar y conservar fondos bibliográficos, así como promover la investigación y difundir la información bibliográfica.",
                                            "url": "https://www.bne.es"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los parámetros introducidos no son validos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La biblioteca con ese id no existe en la base de datos"
            )
    })
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<BibliotecaResponse> edit(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para editar un documento",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateBibliotecaCmd.class),
                            examples = @ExampleObject("""
                                    {
                                            "cityName": "Madrid",
                                            "libraryName": "Biblioteca Nacional de España",
                                            "foundationDate": "1712-12-29",
                                            "numBooks": 30000000,
                                            "description": "La Biblioteca Nacional de España es un centro bibliográfico español que tiene por objetivo reunir, catalogar y conservar fondos bibliográficos, así como promover la investigación y difundir la información bibliográfica.",
                                            "url": "https://www.bne.es"
                                    }
                                    """)
                    )
            )
            @RequestBody CreateBibliotecaCmd cmd,
            @Parameter(
                    description = "ID de la biblioteca a editar",
                    required = true
            )
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.edit(cmd, id));
    }

    @Operation(summary = "Eliminar biblioteca de la base de datos")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Biblioteca eliminada exitosamente",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La biblioteca con ese id no existe en la base de datos."
            )
    })
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> delete(
            @Parameter(
                    description = "ID de la biblioteca a eliminar",
                    required = true
            )
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
