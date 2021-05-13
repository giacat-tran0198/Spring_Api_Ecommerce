package com.crm.gestionstock.validator;

import com.crm.gestionstock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();
        if (articleDto == null) {
            errors.add("Veuillez renseigner le code d'article");
            errors.add("Veuillez renseigner le designation d'article");
            errors.add("Veuillez renseigner le prix unitaire HT d'article");
            errors.add("Veuillez renseigner le prix unitaire TTC d'article");
            errors.add("Veuillez renseigner le taux TVA d'article");
            errors.add("Veuillez selectionner un categorie d'article");
        } else {
            if (!StringUtils.hasLength(articleDto.getCodeArticle())) {
                errors.add("Veuillez renseigner le code d'article");
            }
            if (!StringUtils.hasLength(articleDto.getDesignation())) {
                errors.add("Veuillez renseigner le designation d'article");
            }
            if (articleDto.getPrixUnitaireHt() == null) {
                errors.add("Veuillez renseigner le prix unitaire HT d'article");
            }
            if (articleDto.getPrixUnitaireTtc() == null) {
                errors.add("Veuillez renseigner le prix unitaire TTC d'article");
            }
            if (articleDto.getTauxtva() == null) {
                errors.add("Veuillez renseigner le taux TVA d'article");
            }
            if (articleDto.getCategory() == null) {
                errors.add("Veuillez selectionner un categorie d'article");
            }
        }
        return errors;
    }
}
