package com.crm.gestionstock.repository;

import com.crm.gestionstock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Integer, Client> {
}
