package com.mmonge.game.gacha.model.pojo;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ObjetCollectionRegroupePojo {
    private String categorie;
    private List<ObjetCollectionDTO> objets;
}
