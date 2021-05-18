package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "categories")
public interface CategoryApi {
    @PostMapping(value = APP_ROOT + "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "Cette methode permet d'enregistrer ou modifier une categorie", response =
            CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet category cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet category n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par ID", notes = "Cette methode permet de chercher une categorie par son ID", response =
            CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par CODE", notes = "Cette methode permet de chercher une categorie par son CODE", response =
            CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun categorie n'existe dans la BDD avec le CODE fourni")
    })
    CategoryDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent "
            + "dans la BDD", response = CategoryDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories")
    @ApiOperation(value = "Supprimer un categorie", notes = "Cette methode permet de supprimer une categorie par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete supprime")
    })
    void delete(@PathVariable Integer id);
}
