package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.ObjetCollectionMapper;
import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.services.repository.ObjetCollectionRepository;
import com.mmonge.game.gacha.utils.RareteUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetCollectionService {

    private final ObjetCollectionRepository objetCollectionRepository;
    private final ObjetCollectionMapper objetCollectionMapper;
    private final RareteUtils rareteUtils;

    public ObjetCollectionService(ObjetCollectionRepository objetCollectionRepository, ObjetCollectionMapper objetCollectionMapper, RareteUtils rareteUtils) {
        this.objetCollectionRepository = objetCollectionRepository;
        this.objetCollectionMapper = objetCollectionMapper;
        this.rareteUtils = rareteUtils;
    }

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
    public ObjetCollectionDTO obtenirUnObjet() {
        ObjetCollectionEntity objet = null;
        while (objet == null) {
            objet = objetCollectionRepository.getAleatoireByRarete(rareteUtils.getRareteAleatoire().getCode());
        }
        return objetCollectionMapper.objetCollectionEntityToDto(objet);
    }
}