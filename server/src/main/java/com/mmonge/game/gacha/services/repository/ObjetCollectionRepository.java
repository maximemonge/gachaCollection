package com.mmonge.game.gacha.services.repository;

import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetCollectionRepository extends JpaRepository<ObjetCollectionEntity, Long> {

    @Query("SELECT o from ObjetCollectionEntity o where o.rarete = :rarete ORDER BY RAND() LIMIT 1")
    ObjetCollectionEntity getAleatoireByRarete(@Param("rarete") String rarete);
}
