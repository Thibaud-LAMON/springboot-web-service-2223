package com.lamon_dhon.service.exceptions;

public class OrderNotFoundException extends RuntimeException { // RuntimeException est une classe mère pour toutes les exceptions qui peuvent être lancées pendant l'exécution normale de Java.

    // Nous définissons un constructeur pour notre exception.
    // Un constructeur est une méthode spéciale qui est utilisée pour initialiser un objet.
    // Dans notre cas, notre constructeur prend un argument : l'identifiant de la commande qui n'a pas été trouvée.
    public OrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
        // Nous appelons le constructeur de la classe parent (RuntimeException) avec un message d'erreur spécifique.
        // Ce message sera affiché lorsque l'exception sera lancée.
    }
}
