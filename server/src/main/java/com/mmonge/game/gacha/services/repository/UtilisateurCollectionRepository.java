package com.mmonge.game.gacha.services.repository;

import com.mmonge.game.gacha.model.entity.UtilisateurCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UtilisateurCollectionRepository extends JpaRepository<UtilisateurCollectionEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO UTILISATEUR_COLLECTION (uco_usr_id, uco_oac_id) values (:utilisateur, :objet) ON DUPLICATE KEY UPDATE uco_quantite = uco_quantite + 1", nativeQuery = true)
    void ajouterObjetDansLaCollection(@Param("utilisateur") Long utilisateur, @Param("objet") Long objet);
}
