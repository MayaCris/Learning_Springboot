package com.fixedAssets.web.controller;


import com.fixedAssets.domain.FixedAssetDo;
import com.fixedAssets.domain.service.FixedAssetService;
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
@RequestMapping("/fixedAsset")
public class FixedAssetController {

    @Autowired
    private FixedAssetService fixedAssetService;

    @Operation(summary = "Obtener todos los activos fijos", description = "Retorna una lista de todos los activos fijos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FixedAssetDo.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<FixedAssetDo>> getAll(){
        return new ResponseEntity<>(fixedAssetService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Encontrar activos fijos por ID", description = "Retorna una lista de activos fijos que coinciden con el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FixedAssetDo.class))),
            @ApiResponse(responseCode = "404", description = "Activo fijo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/{personIdD}")
    public ResponseEntity<List<FixedAssetDo>> getByResponsiblePersonDo(@PathVariable("personIdD") String personIdD){
        return fixedAssetService.getByResponsiblePersonDo(personIdD).map(fixedAssetDo -> new ResponseEntity<>(fixedAssetDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Encontrar activos fijos con valor de adquisición menor a:", description = "Retorna una lista de activos fijos que tienen un valor de adquisición menor al proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FixedAssetDo.class))),
            @ApiResponse(responseCode = "404", description = "Activo fijo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/value/{acquisitionValueD}")
    public ResponseEntity<List<FixedAssetDo>> getMinimumQuantityDo(
            @Parameter(description = "Valor de adquisición", required = true)
            @PathVariable("acquisitionValueD") int acquisitionValueD){
        return fixedAssetService.getMinimumQuantityDo(acquisitionValueD).map(fixedAssetDo -> new ResponseEntity<>(fixedAssetDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Guardar un nuevo activo fijo", description = "Guarda un nuevo activo fijo en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activo fijo creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FixedAssetDo.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<FixedAssetDo> save(@RequestBody FixedAssetDo fixedAssetDo){
        return new ResponseEntity<>(fixedAssetService.save(fixedAssetDo), HttpStatus.CREATED);
    }

}
