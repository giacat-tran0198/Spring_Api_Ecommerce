package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;


public interface CategoryApi {
    @PostMapping(value = APP_ROOT + "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable String code);

    @GetMapping(value = APP_ROOT + "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories")
    void delete(@PathVariable Integer id);
}
