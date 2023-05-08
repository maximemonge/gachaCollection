package com.mmonge.game.gacha.utils;

import com.mmonge.game.gacha.model.enums.RareteEnum;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class AleatoireUtils {

    public int genererNombreAleatoire() {
        Random random = new Random();
        random.setSeed(new Date().getTime());
        return random.nextInt(RareteEnum.F.getChance()) + 1;
    }
}
