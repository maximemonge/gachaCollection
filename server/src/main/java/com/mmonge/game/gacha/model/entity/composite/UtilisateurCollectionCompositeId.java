package com.mmonge.game.gacha.model.entity.composite;

import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UtilisateurCollectionCompositeId implements Serializable {
    private UtilisateurEntity utilisateur;
    private ObjetCollectionEntity objetCollection;
}
