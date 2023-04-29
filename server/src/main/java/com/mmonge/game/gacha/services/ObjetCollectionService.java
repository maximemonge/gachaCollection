package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ObjetCollectionService {

    /**
     * Récupère tous les objets de collection
     * @return une liste d'objets de collection
     */
    List<ObjetCollectionDTO> findAll();
}