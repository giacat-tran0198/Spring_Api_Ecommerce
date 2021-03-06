package com.crm.gestionstock.controller;

import com.crm.gestionstock.controller.api.CommandeClientApi;
import com.crm.gestionstock.dto.CommandeClientDto;
import com.crm.gestionstock.dto.LigneCommandeClientDto;
import com.crm.gestionstock.model.enums.EtatCommande;
import com.crm.gestionstock.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private final CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer id, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeClientService.updateEtatCommande(id, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantite(Integer id, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(id, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantite(Integer id, Integer idClient) {
        return ResponseEntity.ok(commandeClientService.updateClient(id, idClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateArticle(Integer id, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(commandeClientService.updateArticle(id, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateArticle(Integer id, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeClientService.deleteArticle(id, idLigneCommande));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCommandeClientDto>> findAllLigneCommandesClientByCommandeClientId(Integer idCommande) {
        return ResponseEntity.ok(commandeClientService.findAllLigneCommandeClientByCommandeClientId(idCommande));
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
