package com.crm.gestionstock.repository;

import com.crm.gestionstock.model.CommandeClient;
import com.crm.gestionstock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
    List<CommandeFournisseur> findAllByFournisseurId(Integer id);
}
