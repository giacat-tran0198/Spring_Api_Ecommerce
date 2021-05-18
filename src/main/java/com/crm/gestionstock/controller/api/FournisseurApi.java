package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "fournisseurs")
public interface FournisseurApi {
    @PostMapping(value = APP_ROOT + "/fournisseurs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un fournisseur", notes = "Cette methode permet d'enregistrer ou modifier un fournisseur", response =
            FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet fournisseur n'est pas valide")
    })
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un fournisseur par ID", notes = "Cette methode permet de chercher un fournisseur par son ID", response =
            FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    FournisseurDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des fournisseurs", notes = "Cette methode permet de chercher et renvoyer la liste des fournisseurs qui existent "
            + "dans la BDD", response = FournisseurDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / Une liste vide")
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs")
    @ApiOperation(value = "Supprimer un fournisseur", notes = "Cette methode permet de supprimer un fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur a ete supprime")
    })
    void delete(@PathVariable Integer id);
}
