package com.mmonge.game.gacha.utils;

import com.mmonge.game.gacha.model.enums.RareteEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AleatoireUtilsTest {

    private AleatoireUtils aleatoireUtils;

    @BeforeEach
    public void beforeEach() {
        aleatoireUtils = new AleatoireUtils();
    }

    @Test
    public void test_genererNombreAleatoire() {
        // When
        int res = aleatoireUtils.genererNombreAleatoire();

        // Then
        assertTrue(res >= 0 && res <= RareteEnum.F.getChance());
    }
}
