package com.crm.gestionstock.repository;

import com.crm.gestionstock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Integer> {
}