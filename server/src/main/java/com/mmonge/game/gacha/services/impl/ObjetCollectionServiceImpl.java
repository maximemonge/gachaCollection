package com.mmonge.game.gacha.services.impl;

import com.mmonge.game.gacha.mapper.ObjetCollectionMapper;
import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.services.ObjetCollectionService;
import com.mmonge.game.gacha.services.impl.repository.ObjetCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetCollectionServiceImpl implements ObjetCollectionService {

    private ObjetCollectionRepository objetCollectionDaoService;
    private ObjetCollectionMapper objetCollectionMapper;

    @Override
    public List<ObjetCollectionDTO> findAll() {
        return objetCollectionMapper.objetCollectionEntityToDtos(objetCollectionDaoService.findAll());
    }

    @Override
    public List<ObjetCollectionDTO> findAllByUtilisateurId(Long utilisateurId) {
        return objetCollectionMapper.objetCollectionEntityToDtos(objetCollectionDaoService.findAllByUtilisateurId(utilisateurId));
    }

    @Autowired
    public void setObjetCollectionDaoService(ObjetCollectionRepository objetCollectionDaoService) {
        this.objetCollectionDaoService = objetCollectionDaoService;
    }

    @Autowired
    public void setObjetCollectionMapper(ObjetCollectionMapper objetCollectionMapper) {
        this.objetCollectionMapper = objetCollectionMapper;
    }
}