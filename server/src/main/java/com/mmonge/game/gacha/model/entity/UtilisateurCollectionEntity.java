package com.mmonge.game.gacha.model.entity;

import com.mmonge.game.gacha.model.entity.composite.UtilisateurCollectionCompositeId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "UTILISATEUR_COLLECTION")
@Getter
@Setter
@IdClass(UtilisateurCollectionCompositeId.class)
public class UtilisateurCollectionEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "uco_usr_id", nullable = false)
    private UtilisateurEntity utilisateur;

    @Id
    @ManyToOne
    @JoinColumn(name = "uco_oac_id")
    private ObjetCollectionEntity objetCollection;

    @Column(name = "uco_quantite")
    private String quantite;
}
