package com.lamon_dhon.repository;

import com.lamon_dhon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// annotation spéciale de Spring qui indique que cette interface est un repository,
// Un mécanisme d'accès aux données qui peut stocker, récupérer et rechercher des données.
// En utilisant cette annotation, Spring  peut fournir des fonctionnalités supplémentaires accordées au repository, telles que la traduction des exceptions.
public interface OrderRepository extends JpaRepository<Order, Long> {
    // JpaRepository est une interface Spring Data qui fournit des méthodes pour les opérations CRUD (Create, Read, Update, Delete) standard.
    // OrderRepository hérite de toutes ces opérations CRUD pour notre classe Order.
    // Le premier paramètre est le type de l'entité pour laquelle le repository est créé (dans ce cas, Order).
    // Le deuxième paramètre est le type de la clé primaire de cette entité (dans ce cas, Long).
    // Nous n'avons pas besoin de fournir une implémentation pour cette interface.
    // Spring Data JPA crée automatiquement une implémentation à l'exécution en se basant sur les noms de méthode que nous avons définis dans l'interface.
}
