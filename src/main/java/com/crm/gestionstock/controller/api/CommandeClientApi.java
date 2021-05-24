package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.CommandeClientDto;
import com.crm.gestionstock.dto.LigneCommandeClientDto;
import com.crm.gestionstock.model.enums.EtatCommande;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PatchMapping(value = APP_ROOT + "/commandeclients/etat/{id}/{etatCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Changer l'etat commande d'une commande client", notes = "Cette methode permet de modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client modifie"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable Integer id, @PathVariable EtatCommande etatCommande);

    @PatchMapping(value = APP_ROOT + "/commandeclients/quantite/{id}/{idLigneCommande}/quantite", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Changer la quantite commande d'une commande client", notes = "Cette methode permet de modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client modifie"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> updateQuantite(@PathVariable Integer id, @PathVariable Integer idLigneCommande, @PathVariable BigDecimal quantite);

    @PatchMapping(value = APP_ROOT + "/commandeclients/client/{id}/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Mettre à jour client d'une commande client", notes = "Cette methode permet de modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client modifie"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> updateQuantite(@PathVariable Integer id, @PathVariable Integer idClient);

    @PatchMapping(value = APP_ROOT + "/commandeclients/article/{id}/{idLigneCommande}/{idArticle}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Mettre à jour article d'une commande client", notes = "Cette methode permet de modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client modifie"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable Integer id, @PathVariable Integer idLigneCommande, @PathVariable Integer idArticle);

    @DeleteMapping(value = APP_ROOT + "/commandeclients/article/{id}/{idLigneCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer d'une commande client", notes = "Cette methode permet de supprimer une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client modifie"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable Integer id, @PathVariable Integer idLigneCommande);

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

    @GetMapping(value = APP_ROOT + "/commandeclients/lignecommande/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des lignes commande clients by commande id", notes = "Cette methode permet de chercher et renvoyer la liste des commande clients qui existent "
            + "dans la BDD", response = LigneCommandeClientDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commande client / Une liste vide")
    })
    ResponseEntity<List<LigneCommandeClientDto>> findAllLigneCommandesClientByCommandeClientId(@PathVariable Integer idCommande);

    @DeleteMapping(value = APP_ROOT + "/commandeclients/{id}")
    @ApiOperation(value = "Supprimer une commande client", notes = "Cette methode permet de supprimer une commande client par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a ete supprime")
    })
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
