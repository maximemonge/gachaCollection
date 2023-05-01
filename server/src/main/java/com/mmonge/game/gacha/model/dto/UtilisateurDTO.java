package com.mmonge.game.gacha.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UtilisateurDTO implements Serializable {
    private Long id;
    private String identifiant;
}
