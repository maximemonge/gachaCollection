package com.mmonge.game.gacha.utils;

import com.mmonge.game.gacha.model.enums.RareteEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RareteUtilsTest {

    private RareteUtils rareteUtils;
    @Mock
    private AleatoireUtils aleatoireUtils;

    @BeforeEach
    public void beforeAll() {
        rareteUtils = new RareteUtils(aleatoireUtils);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 50})
    public void test_getRareteAleatoire_commun_min(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.C, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {51, 80})
    public void test_getRareteAleatoire_peuCommun(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.S, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {81, 90})
    public void test_getRareteAleatoire_rare(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.R, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {91, 96})
    public void test_getRareteAleatoire_epique(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.E, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {97, 99})
    public void test_getRareteAleatoire_legendaire(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.L, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {100, Integer.MAX_VALUE})
    public void test_getRareteAleatoire_mythique(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.M, rareteUtils.getRareteAleatoire());
    }
}
