package com.lamon_dhon.service.exceptions;

public class ArticleNotFoundException extends RuntimeException { // RuntimeException est une classe mère pour toutes les exceptions qui peuvent être lancées pendant l'exécution normale de Java.

    // Nous définissons un constructeur pour notre exception.
    // Un constructeur est une méthode spéciale qui est utilisée pour initialiser un objet.
    // Dans notre cas, notre constructeur prend un argument : l'identifiant de l'article qui n'a pas été trouvé.
    public ArticleNotFoundException(Long id) {
        super("Article not found with id: " + id);
        // Nous appelons le constructeur de la classe parent (RuntimeException) avec un message d'erreur spécifique.
        // Ce message sera affiché lorsque l'exception sera lancée.
    }
}