package com.crm.gestionstock.services;

import com.crm.gestionstock.dto.UtilisateurDto;
import com.crm.gestionstock.dto.auth.ChangerMotDePasseUtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save (UtilisateurDto dto);
    UtilisateurDto findById(Integer id);
    UtilisateurDto findByEmail(String email);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);
}
