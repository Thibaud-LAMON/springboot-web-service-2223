package com.lamon_dhon.api;

import com.lamon_dhon.api.dto.OrderDTO;
import com.lamon_dhon.model.Article;
import com.lamon_dhon.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//indique que cette classe est un contrôleur REST qui gère les requêtes à l'adresse "/api/v1/Orders"
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/Orders", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    // On injecte l'instance de OrderService pour pouvoir l'utiliser dans cette classe
    private final com.lamon_dhon.service.OrderService OrderService;

    // Cette méthode gère les requêtes GET à l'adresse "/api/v1/Orders"
    @GetMapping
    ResponseEntity<List<OrderDTO>> getOrder(){
        List<Order> list = OrderService.getAllOrders(); // On appelle la méthode getAllOrders de OrderService pour obtenir toutes les commandes
        List<OrderDTO> listDTO = mapToDTO(list); // On transforme les objets Order en objets OrderDTO pour renvoyer une réponse au client
        return ResponseEntity.ok(listDTO); // On renvoie la liste d'OrderDTO avec le statut HTTP 200 (OK)
    }

    // Cette méthode gère les requêtes GET à l'adresse "/api/v1/Orders/{id}" où {id} est un paramètre d'URL
    @GetMapping("/{id}")
    ResponseEntity<OrderDTO> getOneOrder(@PathVariable Long id){
        Order order = OrderService.getOneOrder(id); // On appelle la méthode getOneOrder de OrderService pour obtenir la commande correspondant à l'id
        OrderDTO responseDTO = mapToDTO(order); // On transforme l'objet Order en objet OrderDTO pour renvoyer une réponse au client
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    ResponseEntity<OrderDTO> postOrder(@RequestBody OrderDTO orderDTO){
        Order order = OrderService.createOneOrder(orderDTO); // On appelle la méthode createOneOrder de OrderService pour créer un nouvel order avec les informations reçues
        OrderDTO responseDTO = mapToDTO(order);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<OrderDTO> putOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        Order order = OrderService.updateOneOrder(id, orderDTO); // On appelle la méthode updateOneOrder de OrderService pour mettre à jour la commande correspondant à l'id avec les nouvelles informations
        OrderDTO responseDTO = mapToDTO(order);
        return ResponseEntity.ok(responseDTO);
    }

    // Cette méthode gère les requêtes DELETE à l'adresse "/api/v1/Orders/{id}" où {id} est un paramètre d'URL
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        OrderService.deleteOneOrder(id); // On appelle la méthode deleteOneOrder de OrderService pour supprimer la commande correspondant à l'id
        return ResponseEntity.ok().build(); // On renvoie le statut HTTP 200 (OK) sans contenu
    }

    // Cette méthode transforme une liste d'Order en une liste d'OrderDTO
    private List<OrderDTO> mapToDTO(List<Order> list){
        return list
                .stream() // On crée un flux à partir de la liste
                .map(this::mapToDTO) // On transforme chaque Order en OrderDTO
                .toList(); // On collecte les résultats dans une nouvelle liste
    }

    // Cette méthode transforme un Order en OrderDTO
    private OrderDTO mapToDTO(Order order){
        List<Long> ordersID = new ArrayList<>();

        // Si la commande contient des articles, on récupère leurs ids
        if (order.getArticles() != null){
            ordersID = order.getArticles().stream().map(Article::getId).toList();
        }

        // On crée un nouvel OrderDTO avec les informations de l'Order
        return new OrderDTO(
                order.getId(),
                order.getNbArticle(),
                order.getCost(),
                ordersID
        );
    }
}