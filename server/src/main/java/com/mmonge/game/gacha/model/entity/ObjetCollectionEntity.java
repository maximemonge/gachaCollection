package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "OBJET_A_COLLECTIONNER")
@Getter
@Setter
public class ObjetCollectionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "oac_id")
    private Long id;

    @Column(name = "oac_code")
    private String code;

    @Column(name = "oac_rarete")
    private String rarete;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="oac_img_id", referencedColumnName = "img_id")
    private ImageEntity image;
}
