package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "commandefournisseurs")
public interface CommandeFournisseurApi {
    @PostMapping(value = APP_ROOT + "/commandefournisseurs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande fournisseur", notes = "Cette methode permet d'enregistrer ou modifier une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/commandefournisseurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par ID", notes = "Cette methode permet de chercher une commande fournisseur par son ID", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    CommandeFournisseurDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/commandefournisseurs/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par CODE", notes = "Cette methode permet de chercher une commande fournisseur par son CODE", response =
            CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'existe dans la BDD avec le CODE fourni")
    })
    CommandeFournisseurDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/commandefournisseurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des commande fournisseurs", notes = "Cette methode permet de chercher et renvoyer la liste des commande fournisseurs qui existent "
            + "dans la BDD", response = CommandeFournisseurDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commande fournisseur / Une liste vide")
    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandefournisseurs/{id}")
    @ApiOperation(value = "Supprimer une commande fournisseur", notes = "Cette methode permet de supprimer une commande fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a ete supprime")
    })
    void delete(@PathVariable Integer id);
}
