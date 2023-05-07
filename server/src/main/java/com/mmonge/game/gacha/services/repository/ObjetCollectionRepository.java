package com.mmonge.game.gacha.services.repository;

import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetCollectionRepository extends JpaRepository<ObjetCollectionEntity, Long> {
    @Query(value =
            "SELECT obj.oac_id from OBJET_A_COLLECTIONNER obj, UTILISATEUR_COLLECTION usr_coll, UTILISATEUR usr " +
                    "WHERE usr.usr_id = usr_coll.uco_usr_id AND usr_coll.uco_oac_id = obj.oac_id " +
                    "AND usr.usr_id = :utilisateurId", nativeQuery = true)
    List<Long> findAllByUtilisateurId(@Param("utilisateurId") Long utilisateurId);

    @Query("SELECT o from ObjetCollectionEntity o where o.rarete = :rarete ORDER BY RAND() LIMIT 1")
    ObjetCollectionEntity getAleatoireByRarete(@Param("rarete") String rarete);
}
