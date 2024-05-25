package com.mmonge.game.gacha.model.entity.composite;

import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;

import java.io.Serializable;

public class UtilisateurCollectionCompositeId implements Serializable {
    private UtilisateurEntity utilisateur;
    private ObjetCollectionEntity objetCollection;

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
}
