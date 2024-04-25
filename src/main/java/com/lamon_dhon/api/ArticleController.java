package com.lamon_dhon.api;

import com.lamon_dhon.api.dto.ArticleDTO;
import com.lamon_dhon.model.Article;
import com.lamon_dhon.model.Order;
import com.lamon_dhon.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//indique que cette classe est un contrôleur REST qui gère les requêtes à l'adresse "/api/v1/Articles"
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/Articles", produces = APPLICATION_JSON_VALUE)
public class ArticleController {

    // On injecte l'instance de ArticleService pour pouvoir l'utiliser dans cette classe
    private final ArticleService ArticleService;

    // Cette méthode gère les requêtes GET à l'adresse "/api/v1/Articles"
    @GetMapping
    ResponseEntity<List<ArticleDTO>> getArticle(){
        List<Article> list = ArticleService.getAllArticles(); // On appelle la méthode getAllArticles de ArticleService pour obtenir tous les articles
        List<ArticleDTO> listDTO = mapToDTO(list); // On transforme les objets Article en objets ArticleDTO pour renvoyer une réponse au client
        return ResponseEntity.ok(listDTO); // On renvoie la liste d'ArticleDTO avec le statut HTTP 200 (OK)
    }

    @GetMapping("/{id}")
    ResponseEntity<ArticleDTO> getOneArticle(@PathVariable Long id){
        Article article = ArticleService.getOneArticle(id);
        ArticleDTO responseDTO = mapToDTO(article);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    ResponseEntity<ArticleDTO> postArticle(@RequestBody ArticleDTO articleDTO){
        Article article = ArticleService.createOneArticle(articleDTO);
        ArticleDTO listDTO = mapToDTO(article);
        return ResponseEntity.ok(listDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<ArticleDTO> putArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO){
        Article article = ArticleService.updateOneArticle(id, articleDTO);
        ArticleDTO responseDTO = mapToDTO(article);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        ArticleService.deleteOneArticle(id);
        return ResponseEntity.noContent().build();
    }


    private List<ArticleDTO> mapToDTO(List<Article> list){
        return list
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private ArticleDTO mapToDTO(Article article){
        List<Long> articlesID = new ArrayList<>();

        // Si un article figure dans une commande...
        if (article.getOrders() != null){
           articlesID =  article.getOrders().stream().map(Order::getId).toList();
            // le DTO transforme la liste de commandes en Stream
            // puis il appelle la méthode getId pour chaque commande dans le Stream
            // finalement, il convertit le Stream en List
        }

        return new ArticleDTO(
                article.getId(),
                article.getDesignation(),
                article.getQuantity(),
                article.getPrice(),
                articlesID
        );
    }
}
