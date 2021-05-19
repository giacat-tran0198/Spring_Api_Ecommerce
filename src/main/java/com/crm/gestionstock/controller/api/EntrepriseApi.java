package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "entreprises")
public interface EntrepriseApi {
    @PostMapping(value = APP_ROOT + "/enreprises", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une entreprise", notes = "Cette methode permet d'enregistrer ou modifier une entreprise", response =
            EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet entreprise n'est pas valide")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = APP_ROOT + "/enreprises/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une entreprise par ID", notes = "Cette methode permet de chercher une entreprise par son ID", response =
            EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune entreprise n'existe dans la BDD avec l'ID fourni")
    })
    EntrepriseDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/enreprises", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des entreprises", notes = "Cette methode permet de chercher et renvoyer la liste des entreprises qui existent "
            + "dans la BDD", response = EntrepriseDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des entreprises / Une liste vide")
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/enreprises/{id}")
    @ApiOperation(value = "Supprimer une entreprise", notes = "Cette methode permet de supprimer une entreprise par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a ete supprime")
    })
    void delete(@PathVariable Integer id);
}
