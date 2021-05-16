package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;


public interface FournisseurApi {
    @PostMapping(value = APP_ROOT + "/suppliers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = APP_ROOT + "/suppliers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable Integer id);
    
    @GetMapping(value = APP_ROOT + "/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/suppliers")
    void delete(@PathVariable Integer id);
}
