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
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 50000})
    public void test_getRareteAleatoire_commun_min(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.C, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {50001, 80000})
    public void test_getRareteAleatoire_peuCommun(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.S, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {80001, 90000})
    public void test_getRareteAleatoire_rare(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.R, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {90001, 96000})
    public void test_getRareteAleatoire_epique(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.E, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {96001, 99000})
    public void test_getRareteAleatoire_legendaire(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.L, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {99001, 99999})
    public void test_getRareteAleatoire_mythique(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.M, rareteUtils.getRareteAleatoire());
    }

    @ParameterizedTest
    @ValueSource(ints = {100000, Integer.MAX_VALUE})
    public void test_getRareteAleatoire_fantome(int nombreAleatoire) {
        Mockito.when(aleatoireUtils.genererNombreAleatoire()).thenReturn(nombreAleatoire);

        assertEquals(RareteEnum.F, rareteUtils.getRareteAleatoire());
    }
}
