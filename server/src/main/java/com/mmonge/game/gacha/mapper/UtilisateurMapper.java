package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.UtilisateurDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurMapper {

    public UtilisateurDTO utilisateurEntityToDto(UtilisateurEntity entity) {
        UtilisateurDTO dto = new UtilisateurDTO();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setIdentifiant(entity.getIdentifiant());
        }
        return dto;
    }
}
