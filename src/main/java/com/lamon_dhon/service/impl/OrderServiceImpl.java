package com.lamon_dhon.service.impl;

import com.lamon_dhon.api.dto.ArticleDTO;
import com.lamon_dhon.api.dto.OrderDTO;
import com.lamon_dhon.model.Article;
import com.lamon_dhon.model.Order;
import com.lamon_dhon.service.ArticleService;
import com.lamon_dhon.service.OrderService;
import com.lamon_dhon.repository.OrderRepository;
import com.lamon_dhon.service.exceptions.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Annotation qui indique à Spring que cette classe est un service
@RequiredArgsConstructor // Annotation de Lombok pour générer automatiquement un constructeur avec des paramètres pour les champs final
public class OrderServiceImpl implements OrderService {

    // Dépendances nécessaires pour ce service
    private final ArticleService articleService;
    private final OrderRepository orderRepository;

    @Override
    // Récupère toutes les commandes existantes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    // Récupère une commande spécifique en fonction de son id
    public Order getOneOrder(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    // Créer une nouvelle commande à partir d'un DTO (Data Transfer Object)
    public Order createOneOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setNbArticle(orderDTO.nbArticle());
        order.setCost(orderDTO.cost());

        // Récupère tous les articles dans la commande à partir de leur ID
        List<Article> articlesInOrder = orderDTO.articleId().stream().map(articleService::getOneArticle).collect(Collectors.toList());

        for (Article article : articlesInOrder) {
            // Réduit la quantité de chaque article
            Integer newQuantity = article.getQuantity() - 1;  // On suppose que la quantité de chaque article dans l'ordre est de 1

            // Crée un nouveau DTO d'article avec une quantité réduite
            ArticleDTO articleDTO = new ArticleDTO(article.getId(), article.getDesignation(), newQuantity, article.getPrice(), null);

            // Met à jour l'article
            articleService.updateOneArticle(article.getId(), articleDTO);
        }

        order.setArticles(articlesInOrder);

        return orderRepository.save(order); // Sauvegarde la commande dans la base de données
    }

    @Override
    // Met à jour une commande existante
    public Order updateOneOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setNbArticle(orderDTO.nbArticle());
            order.setCost(orderDTO.cost());
            order.setArticles(orderDTO.articleId().stream().map(articleService::getOneArticle).toList());
            // Cette ligne de code transforme la liste d'identifiants d'articles de l'ordreDTO en une liste d'objets Article.
            // En utilisant la méthode stream() nous obtenons une séquence d'éléments de la liste d'identifiants d'articles.
            // Avec la méthode map(), nous appliquons la méthode getOneArticle de l'articleService à chaque identifiant d'article dans le flux,
            // ce qui donne un nouvel flux d'articles.
            // Finalement, la méthode toList() convertit ce flux d'articles en une liste, et nous affectons cette liste à l'ordre avec setArticles().


            return orderRepository.save(order); // Sauvegarde l'ordre mis à jour dans la base de données
        }
        throw new OrderNotFoundException(id); // Si la commande n'a pas été trouvée, une exception est lancée
    }

    @Override
    // Supprime une commande existante
    public void deleteOneOrder(Long id) {
        orderRepository.deleteById(id);
    }
}