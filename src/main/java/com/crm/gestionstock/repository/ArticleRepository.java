package com.crm.gestionstock.repository;

import com.crm.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findArticleByCodeArticle(String codeArticle);
    List<Article> findAllByCategoryId(Integer idCategory);
}