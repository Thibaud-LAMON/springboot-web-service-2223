package com.lamon_dhon.service.impl;

import com.lamon_dhon.api.dto.ArticleDTO;
import com.lamon_dhon.model.Article;
import com.lamon_dhon.repository.ArticleRepository;
import com.lamon_dhon.service.ArticleService;
import com.lamon_dhon.service.exceptions.ArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    // Dépendance nécessaire pour ce service
    private final ArticleRepository articleRepository;

    @Override
    // Implémentation de la méthode pour récupérer tous les articles.
    // L'annotation @Override est utilisée pour indiquer qu'une méthode de l'interface est implémentée.
    public List<Article> getAllArticles() {
        return articleRepository.findAll(); // Utilise la méthode findAll() de l'interface ArticleRepository pour récupérer tous les articles de la base de données.
    }

    @Override
    public Article getOneArticle(Long id){
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    // Créer un nouvel article à partir d'un DTO (Data Transfer Object)
    public Article createOneArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        // Rempli l'article avec les informations du DTO.
        article.setDesignation(articleDTO.designation());
        article.setQuantity(articleDTO.quantity());
        article.setPrice(articleDTO.price());
        return articleRepository.save(article); // Enregistre l'article dans la base de données avec la méthode save() et renvoie l'article sauvegardé.
    }

    @Override
    public Article updateOneArticle(Long id, ArticleDTO articleDTO) {
        // Essaye de trouver l'article par son id.
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            // Si l'article est trouvé, met à jour ses informations avec celles du DTO.
            Article article = optionalArticle.get();
            article.setDesignation(articleDTO.designation());
            article.setQuantity(articleDTO.quantity());
            article.setPrice(articleDTO.price());
            // Sauvegarde les modifications dans la base de données et renvoie l'article mis à jour.
            return articleRepository.save(article);
        }
        // Dans le cas où l'article n'est pas trouvé, renvoie null (cela pourrait être géré autrement).
        return null;
    }

    @Override
    public void deleteOneArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
