package com.lamon_dhon.service;

import com.lamon_dhon.api.dto.OrderDTO;
import com.lamon_dhon.model.Order;

import java.util.List;

// Déclaration de l'interface OrderService.
// Une interface est une collection de méthodes abstraites (méthodes sans corps).
// C'est un contrat qui spécifie ce qu'une classe doit faire, mais pas comment elle doit le faire.
public interface OrderService {

    // Cette méthode doit renvoyer une liste de toutes les commandes.
    // 'List' est une interface dans la bibliothèque standard Java qui représente une collection ordonnée (également connue sous le nom de séquence).
    // 'Order' est la classe qui définit la structure d'une commande.
    List<Order> getAllOrders();

    Order getOneOrder(Long id);

    Order createOneOrder(OrderDTO orderDTO);

    // Cette méthode doit mettre à jour une commande existante en utilisant l'ID et les nouvelles données fournies dans l'OrderDTO,
    // puis renvoyer la commande mise à jour.
    Order updateOneOrder(Long id, OrderDTO orderDTO);

    // Cette méthode doit supprimer une commande spécifique basée sur l'ID fourni.
    // 'void' signifie que cette méthode ne renvoie rien.
    void deleteOneOrder(Long id);
}