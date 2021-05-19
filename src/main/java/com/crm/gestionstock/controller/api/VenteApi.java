package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.VenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "ventes")
public interface VenteApi {
    @PostMapping(value = APP_ROOT + "/ventes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une vente", notes = "Cette methode permet d'enregistrer ou modifier une vente", response =
            VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet vente n'est pas valide")
    })
    VenteDto save(@RequestBody VenteDto dto);

    @GetMapping(value = APP_ROOT + "/ventes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par ID", notes = "Cette methode permet de chercher une vente par son ID", response =
            VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune vente n'existe dans la BDD avec l'ID fourni")
    })
    VenteDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par CODE", notes = "Cette methode permet de chercher une vente par son CODE", response =
            VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun vente n'existe dans la BDD avec le CODE fourni")
    })
    VenteDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/ventes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des ventes", notes = "Cette methode permet de chercher et renvoyer la liste des ventes qui existent "
            + "dans la BDD", response = VenteDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ventes / Une liste vide")
    })
    List<VenteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/{id}")
    @ApiOperation(value = "Supprimer un vente", notes = "Cette methode permet de supprimer une vente par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a ete supprime")
    })
    void delete(@PathVariable Integer id);
}
