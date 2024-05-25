package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurCollectionEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurCollectionMapper {

    public List<UtilisateurCollectionDTO> utilisateurCollectionEntityToDtos(List<UtilisateurCollectionEntity> entities) {
        List<UtilisateurCollectionDTO> dtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entities)) {
            dtos = entities.stream().map(UtilisateurCollectionMapper::utilisateurCollectionEntityToDto).collect(Collectors.toList());
        }
        return dtos;
    }

    private static UtilisateurCollectionDTO utilisateurCollectionEntityToDto(@NonNull UtilisateurCollectionEntity entity) {
        return new UtilisateurCollectionDTO(entity.getObjetCollection().getId(), entity.getQuantite());
    }
}
