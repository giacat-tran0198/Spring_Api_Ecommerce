package com.crm.gestionstock.repository;

import com.crm.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Integer, Article> {
    List<Article> findByCodeArticleAndDesignationIgnoreCase(String codeArticle, String designation);
}
