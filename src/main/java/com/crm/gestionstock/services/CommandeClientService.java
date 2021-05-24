package com.crm.gestionstock.services;

import com.crm.gestionstock.dto.ClientDto;
import com.crm.gestionstock.dto.CommandeClientDto;
import com.crm.gestionstock.dto.LigneCommandeClientDto;
import com.crm.gestionstock.model.enums.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {
    CommandeClientDto save (CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
    CommandeClientDto updateEtatCommande(Integer id, EtatCommande etatCommande);
    CommandeClientDto updateQuantiteCommande(Integer id, Integer idLigneCommande, BigDecimal quantite);
    CommandeClientDto updateClient(Integer id, Integer idClient);
    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);
    List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(Integer idCommande);
}
