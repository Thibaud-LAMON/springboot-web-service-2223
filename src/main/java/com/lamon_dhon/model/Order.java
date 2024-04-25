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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "nbArticle")
    private Integer nbArticle;


    @Column(nullable = false, name = "cost")
    private float cost;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "orders_has_articles",
            joinColumns = @JoinColumn(name = "Orders_id"),
            inverseJoinColumns = @JoinColumn(name = "Articles_id")
    )

    // Permet d'éviter les problèmes potentiels de boucle infinie lors de la génération de la représentation en chaîne de l'objet.
    @ToString.Exclude
    private List<Article> articles;

    @Override
    public boolean equals(Object o) { //détermine si deux commandes sont "égales"
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id); // deux commandes sont "égales" si elles ont le même id
    }

    @Override
    public int hashCode() { //retourne un entier qui est l'identifiant unique pour l'instance de la commande dans la JVM
        return getClass().hashCode();
    }
}
