package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurCollectionEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurCollectionMapper {

    public List<UtilisateurCollectionDTO> utilisateurCollectionEntityToDtos(List<UtilisateurCollectionEntity> entities) {
        List<UtilisateurCollectionDTO> dtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entities)) {
            for (UtilisateurCollectionEntity entity : entities) {
                if (entity != null && entity.getObjetCollection() != null) {
                    dtos.add(utilisateurCollectionEntityToDto(entity));
                }
            }
        }
        return dtos;
    }

    private UtilisateurCollectionDTO utilisateurCollectionEntityToDto(UtilisateurCollectionEntity entity) {
        return new UtilisateurCollectionDTO(entity.getObjetCollection().getId(), entity.getQuantite());
    }
}
