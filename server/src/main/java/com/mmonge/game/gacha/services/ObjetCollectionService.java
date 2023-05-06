package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.ObjetCollectionMapper;
import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.services.repository.ObjetCollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObjetCollectionService {

    private final ObjetCollectionRepository objetCollectionRepository;
    private final ObjetCollectionMapper objetCollectionMapper;

    /**
     * Récupère tous les objets de collection
     *
     * @return une liste d'objets de collection
     */
    public List<ObjetCollectionDTO> findAll() {
        return objetCollectionMapper.objetCollectionEntityToDtos(objetCollectionRepository.findAll());
    }

    /**
     * Récupère tous les ids des objets de collection d'un utilisateur
     *
     * @param utilisateurId : id de l'utilisateur
     * @return une liste d'objets de collection d'un utilisateur
     */
    public List<Long> findAllIdsByUtilisateurId(Long utilisateurId) {
        return objetCollectionRepository.findAllByUtilisateurId(utilisateurId);
    }
}