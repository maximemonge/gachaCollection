package com.mmonge.game.gacha.services.impl.repository;

import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetCollectionRepository extends JpaRepository<ObjetCollectionEntity, Long> {}
