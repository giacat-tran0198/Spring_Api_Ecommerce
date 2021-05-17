package com.crm.gestionstock.services;

import com.crm.gestionstock.dto.VenteDto;

import java.util.List;

public interface VenteService {
    VenteDto save (VenteDto dto);
    VenteDto findById(Integer id);
    VenteDto findByCode(String code);
    List<VenteDto> findAll();
    void delete(Integer id);
}
