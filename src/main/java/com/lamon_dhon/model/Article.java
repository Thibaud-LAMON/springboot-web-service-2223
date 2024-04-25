package com.lamon_dhon.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false, name = "designation")
    private String designation;

    @Column(nullable = false, name = "quantity")
    private Integer quantity;


    @Column(nullable = false, name = "price")
    private float price;

    @ManyToMany(mappedBy = "articles")

    // Permet d'éviter les problèmes potentiels de boucle infinie lors de la génération de la représentation en chaîne de l'objet.
    @ToString.Exclude
    private List<Order> orders;

    @Override
    public boolean equals(Object o) { //détermine si deux commandes sont "égaux"
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Article article = (Article) o;
        return id != null && Objects.equals(id, article.id); // deux articles sont "égaux" s'ils ont le même id
    }

    @Override
    public int hashCode() { //retourne un entier qui est l'identifiant unique pour l'instance de l'article dans la JVM
        return getClass().hashCode();
    }
}
