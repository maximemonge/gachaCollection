package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.UtilisateurCollectionMapper;
import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurCollectionEntity;
import com.mmonge.game.gacha.services.repository.UtilisateurCollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurCollectionService {

    private final UtilisateurCollectionRepository utilisateurCollectionRepository;
    private final UtilisateurCollectionMapper utilisateurCollectionMapper;

    /**
     * Récupère tous les objets de collection d'un utilisateur
     *
     * @param utilisateurId : id de l'utilisateur
     * @return une liste d'objets de collection d'un utilisateur
     */
    public List<UtilisateurCollectionDTO> findAllIdsByUtilisateurId(Long utilisateurId) {
        List<UtilisateurCollectionEntity> objets = utilisateurCollectionRepository.findAllByUtilisateurId(utilisateurId);
        return utilisateurCollectionMapper.utilisateurCollectionEntityToDtos(objets);
    }

    public void ajouterObjetDansLaCollection(Long utilisateurId, Long objetCollectionId) {
        utilisateurCollectionRepository.ajouterObjetDansLaCollection(utilisateurId, objetCollectionId);
    }
}