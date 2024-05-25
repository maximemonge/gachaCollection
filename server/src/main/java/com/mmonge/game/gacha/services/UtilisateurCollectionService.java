package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.UtilisateurCollectionMapper;
import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurCollectionEntity;
import com.mmonge.game.gacha.services.repository.UtilisateurCollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurCollectionService {

    private final UtilisateurCollectionRepository utilisateurCollectionRepository;
    private final UtilisateurCollectionMapper utilisateurCollectionMapper;

    public UtilisateurCollectionService(UtilisateurCollectionRepository utilisateurCollectionRepository, UtilisateurCollectionMapper utilisateurCollectionMapper) {
        this.utilisateurCollectionRepository = utilisateurCollectionRepository;
        this.utilisateurCollectionMapper = utilisateurCollectionMapper;
    }

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