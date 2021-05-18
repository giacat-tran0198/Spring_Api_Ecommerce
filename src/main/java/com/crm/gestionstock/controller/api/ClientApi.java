package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.ClientDto;
import com.crm.gestionstock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "clients")
public interface ClientApi {
    @PostMapping(value = APP_ROOT + "/clients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client", notes = "Cette methode permet d'enregistrer ou modifier un client", response =
            ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")
    })
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par ID", notes = "Cette methode permet de chercher un client par son ID", response =
            ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune client n'existe dans la BDD avec l'ID fourni")
    })
    ClientDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des clients", notes = "Cette methode permet de chercher et renvoyer la liste des clients qui existent "
            + "dans la BDD", response = ClientDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des clients / Une liste vide")
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients")
    @ApiOperation(value = "Supprimer un client", notes = "Cette methode permet de supprimer un client par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete supprime")
    })
    void delete(@PathVariable Integer id);
}
