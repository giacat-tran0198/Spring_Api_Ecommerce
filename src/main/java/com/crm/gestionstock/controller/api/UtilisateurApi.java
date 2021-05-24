package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.UtilisateurDto;
import com.crm.gestionstock.dto.UtilisateurDto;
import com.crm.gestionstock.dto.auth.ChangerMotDePasseUtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "utilisateus")
public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT + "/utilisateus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un utilisateur", notes = "Cette methode permet d'enregistrer ou modifier un utilisateur", response =
            UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet utilisateur n'est pas valide")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @PostMapping(value = APP_ROOT + "/utilisateus/update/password")
    UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/utilisateus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur par ID", notes = "Cette methode permet de chercher un utilisateur par son ID", response =
            UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La utilisateur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune utilisateur n'existe dans la BDD avec l'ID fourni")
    })
    UtilisateurDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateus/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un utilisateur par CODE", notes = "Cette methode permet de chercher un utilisateur par son CODE", response =
            UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun utilisateur n'existe dans la BDD avec le CODE fourni")
    })
    UtilisateurDto findByEmail(@PathVariable String email);

    @GetMapping(value = APP_ROOT + "/utilisateus", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des utilisateurs", notes = "Cette methode permet de chercher et renvoyer la liste des utilisateurs qui existent "
            + "dans la BDD", response = UtilisateurDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des utilisateurs / Une liste vide")
    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateus/{id}")
    @ApiOperation(value = "Supprimer un utilisateur", notes = "Cette methode permet de supprimer un utilisateur par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a ete supprime")
    })
    void delete(@PathVariable Integer id);
}
