package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "commandeclients")
public interface CommandeClientApi {
    @PostMapping(value = APP_ROOT + "/commandeclients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande client", notes = "Cette methode permet d'enregistrer ou modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @GetMapping(value = APP_ROOT + "/commandeclients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande client par ID", notes = "Cette methode permet de chercher une commande client par son ID", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/commandeclients/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande client par CODE", notes = "Cette methode permet de chercher une commande client par son CODE", response =
            CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/commandeclients", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des commande clients", notes = "Cette methode permet de chercher et renvoyer la liste des commande clients qui existent "
            + "dans la BDD", response = CommandeClientDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commande client / Une liste vide")
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandeclients/{id}")
    @ApiOperation(value = "Supprimer une commande client", notes = "Cette methode permet de supprimer une commande client par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete supprime")
    })
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
