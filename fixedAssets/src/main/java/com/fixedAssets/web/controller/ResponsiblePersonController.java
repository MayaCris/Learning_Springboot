package com.fixedAssets.web.controller;

import com.fixedAssets.domain.ResponsiblePersonDo;
import com.fixedAssets.domain.service.ResponsiblePersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsiblePerson")
public class ResponsiblePersonController {

    @Autowired
    private ResponsiblePersonService responsiblePersonService;

    @Operation(summary = "Obtener todas las personas responsables", description = "Retorna una lista de todas las personas responsables")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponsiblePersonDo.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<ResponsiblePersonDo>> getAll(){
        return new ResponseEntity<>(responsiblePersonService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Encontrar personas responsables por ID", description = "Retorna una lista de personas responsables que coinciden con el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponsiblePersonDo.class))),
            @ApiResponse(responseCode = "404", description = "Persona responsable no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/personId/{personIdD}")
    public ResponseEntity<List<ResponsiblePersonDo>> getBypersonIdD(
            @Parameter(description = "ID de la persona responsable", required = true)
            @PathVariable String personIdD){
        return responsiblePersonService.getBypersonIdD(personIdD).map(responsiblePersonDo -> new ResponseEntity<>(responsiblePersonDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar persona responsable", description = "Elimina una persona responsable de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona responsable eliminada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponsiblePersonDo.class))),
            @ApiResponse(responseCode = "404", description = "Persona responsable no encontrada",
                    content = @Content)
    })
    @DeleteMapping("/delete/{personIdD}")
    public ResponseEntity delete(
            @Parameter(description = "ID de la persona responsable", required = true)
            @PathVariable("personIdD") String personIdD) {
        if (responsiblePersonService.deletePerson(personIdD)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar una nueva persona responsable", description = "Guarda una nueva persona responsable en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Persona responsable creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponsiblePersonDo.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<ResponsiblePersonDo> save(@RequestBody ResponsiblePersonDo responsiblePersonDo){
        return new ResponseEntity<>(responsiblePersonService.savePerson(responsiblePersonDo), HttpStatus.CREATED);
    }

}
