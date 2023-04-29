package com.mmonge.game.gacha.model.dto;

import com.mmonge.game.gacha.model.entity.ImageEntity;
import com.mmonge.game.gacha.model.enums.RareteEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ObjetCollectionDTO implements Serializable {
    private Long id;
    private String code;
    private RareteEnum rarete;
    private ImageDTO image;
}
