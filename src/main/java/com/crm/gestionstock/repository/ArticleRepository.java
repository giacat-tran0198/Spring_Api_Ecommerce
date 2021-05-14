package com.crm.gestionstock.repository;

import com.crm.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

}