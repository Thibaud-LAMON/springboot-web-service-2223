package com.lamon_dhon.api.dto;

import java.util.List;
//DTO : Data Transfert Object
//encapsule les données, est souvent utilisé pour envoyer des données entre une application cliente et un serveur.
/*En Java, une "record" est une sorte de classe qui contient uniquement des données.
Elle a automatiquement des getters, des méthodes equals(), hashCode(), toString() générées.
Les classes "record" sont disponibles depuis la version 16 de Java. */
public record OrderDTO(
        Long id,
        Integer nbArticle,
        Float cost,
        List<Long> articleId
) {
}
