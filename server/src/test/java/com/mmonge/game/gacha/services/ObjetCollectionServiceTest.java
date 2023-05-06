package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.ObjetCollectionMapper;
import com.mmonge.game.gacha.services.repository.ObjetCollectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ObjetCollectionServiceTest {

    @Mock
    private ObjetCollectionRepository objetCollectionRepository;
    @Mock
    private ObjetCollectionMapper objetCollectionMapper;

    private ObjetCollectionService objetCollectionService;

    @BeforeEach
    public void beforeAll() {
        objetCollectionService = new ObjetCollectionService(objetCollectionRepository, objetCollectionMapper);
    }

    @Test
    public void test_findAll() {
        objetCollectionService.findAll();

        Mockito.verify(objetCollectionRepository, Mockito.times(1)).findAll();
        Mockito.verify(objetCollectionMapper, Mockito.times(1)).objetCollectionEntityToDtos(Mockito.any());
    }

    @Test
    public void test_findAllIdsByUtilisateurId() {
        objetCollectionService.findAllIdsByUtilisateurId(1L);

        Mockito.verify(objetCollectionRepository, Mockito.times(1)).findAllByUtilisateurId(1L);
    }
}