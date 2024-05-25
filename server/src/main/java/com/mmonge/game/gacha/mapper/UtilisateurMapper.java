package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.UtilisateurDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurMapper {

    public UtilisateurDTO utilisateurEntityToDto(@NonNull UtilisateurEntity entity) {
        return new UtilisateurDTO(entity.getId(), entity.getIdentifiant(), entity.getMonnaie());
    }
}
