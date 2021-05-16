package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;


public interface ClientApi {
    @PostMapping(value = APP_ROOT + "/clients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients")
    void delete(@PathVariable Integer id);
}
