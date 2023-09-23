package com.mmonge.game.gacha.services.repository;

import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Long> {

    @Query("SELECT u FROM UtilisateurEntity u WHERE u.identifiant = :identifiant")
    public UtilisateurEntity getByIdentifiant(@Param("identifiant") String identifiant);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UtilisateurEntity u WHERE u.identifiant = :identifiant")
    public boolean isIdentifiantUtilise(@Param("identifiant") String identifiant);

    @Modifying
    @Transactional
    @Query("UPDATE UtilisateurEntity usr SET usr.monnaie = usr.monnaie - :cout where usr.id = :utilisateur")
    void retirerMonnaie(@Param("utilisateur") Long utilisateurId, @Param("cout") Long cout);
}
