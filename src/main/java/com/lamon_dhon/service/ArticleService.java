package com.lamon_dhon.service;

import com.lamon_dhon.api.dto.ArticleDTO;
import com.lamon_dhon.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getOneArticle(Long id);
    Article createOneArticle(ArticleDTO articleDTO);
    Article updateOneArticle(Long id, ArticleDTO articleDTO);
    void deleteOneArticle(Long id);
}
