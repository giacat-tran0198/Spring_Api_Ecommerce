package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;


public interface EntrepriseApi {
    @PostMapping(value = APP_ROOT + "/enreprises", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = APP_ROOT + "/enreprises/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable Integer id);
    
    @GetMapping(value = APP_ROOT + "/enreprises", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/enreprises")
    void delete(@PathVariable Integer id);
}
