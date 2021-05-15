package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;


public interface ArticleApi {
    @PostMapping(value = APP_ROOT + "/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/articles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles")
    void delete(@PathVariable Integer id);
}
