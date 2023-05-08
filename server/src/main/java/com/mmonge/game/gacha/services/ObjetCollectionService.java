package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.ObjetCollectionMapper;
import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.services.repository.ObjetCollectionRepository;
import com.mmonge.game.gacha.services.repository.UtilisateurCollectionRepository;
import com.mmonge.game.gacha.utils.RareteUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObjetCollectionService {

    private final ObjetCollectionRepository objetCollectionRepository;
    private final UtilisateurCollectionRepository utilisateurCollectionRepository;
    private final ObjetCollectionMapper objetCollectionMapper;
    private final RareteUtils rareteUtils;

    /**
     * Récupère tous les objets de collection
     *
     * @return une liste d'objets de collection
     */
    public List<ObjetCollectionDTO> findAll() {
        return objetCollectionMapper.objetCollectionEntityToDtos(objetCollectionRepository.findAll());
    }

    /**
     * Récupère un objet aléatoire pour l'utilisateur passé en paramètre
     *
     * @return objet de collection aléatoire
     */
    public ObjetCollectionDTO obtenirUnObjet(Long utilisateurId) {
        ObjetCollectionEntity objet = objetCollectionRepository.getAleatoireByRarete(rareteUtils.getRareteAleatoire().getCode());
        utilisateurCollectionRepository.ajouterObjetDansLaCollection(utilisateurId, objet.getId());
        return objetCollectionMapper.objetCollectionEntityToDto(objet);
    }
}