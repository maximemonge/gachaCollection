package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;

import java.util.List;

public interface ObjetCollectionService {

    /**
     * Récupère tous les objets de collection
     *
     * @return une liste d'objets de collection
     */
    List<ObjetCollectionDTO> findAll();

    /**
     * Récupère tous les ids des objets de collection d'un utilisateur
     *
     * @param utilisateurId : id de l'utilisateur
     * @return une liste d'objets de collection d'un utilisateur
     */
    List<Long> findAllIdsByUtilisateurId(Long utilisateurId);
}
