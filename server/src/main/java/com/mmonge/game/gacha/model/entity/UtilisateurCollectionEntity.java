package com.mmonge.game.gacha.model.entity;

import com.mmonge.game.gacha.model.entity.composite.UtilisateurCollectionCompositeId;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "UTILISATEUR_COLLECTION")
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
    private Long quantite;

    public UtilisateurEntity getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurEntity utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ObjetCollectionEntity getObjetCollection() {
        return objetCollection;
    }

    public void setObjetCollection(ObjetCollectionEntity objetCollection) {
        this.objetCollection = objetCollection;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
}
