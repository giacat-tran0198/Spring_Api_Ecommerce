package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;


public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT + "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(value = APP_ROOT + "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/users/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByEmail(@PathVariable String email);

    @GetMapping(value = APP_ROOT + "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/users")
    void delete(@PathVariable Integer id);
}
