package com.mmonge.game.gacha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UtilisateurCollectionDTO implements Serializable {
    private Long objetCollectionId;
    private Long quantite;
}
