package com.crm.gestionstock.services;

import com.crm.gestionstock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save (FournisseurDto dto);
    FournisseurDto findById(Integer id);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}
