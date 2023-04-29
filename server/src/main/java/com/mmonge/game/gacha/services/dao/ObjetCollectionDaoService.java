package com.mmonge.game.gacha.services.dao;

import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetCollectionDaoService extends JpaRepository<ObjetCollectionEntity, Long> {

    /**
     * Récupère tous les objets de collection
     * @return une liste d'objets de collection
     */
    List<ObjetCollectionEntity> getAll();
}
