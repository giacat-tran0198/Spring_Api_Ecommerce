package com.crm.gestionstock.services;

import com.crm.gestionstock.dto.ArticleDto;
import com.crm.gestionstock.dto.LigneCommandeClientDto;
import com.crm.gestionstock.dto.LigneCommandeFournisseurDto;
import com.crm.gestionstock.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {
    ArticleDto save (ArticleDto dto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String codeArticle);
    List<ArticleDto> findAll();
    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);
    List<LigneCommandeClientDto> findHistoriaueCommandeClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);
    List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);
    void delete(Integer id);
}
