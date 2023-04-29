package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "OBJET_A_COLLECTIONNER")
public class ObjetCollectionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private Long code;

    @Column(name = "RARETE")
    private String rarete;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @Column(name = "IMAGE_ID")
    private ImageEntity image;
}
