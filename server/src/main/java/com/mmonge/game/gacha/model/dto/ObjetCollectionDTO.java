package com.mmonge.game.gacha.model.dto;

import com.mmonge.game.gacha.model.enums.RareteEnum;

public record ObjetCollectionDTO(
        Long id,
        String code,
        RareteEnum rarete,
        ImageDTO image,
        String categorie) {
}
