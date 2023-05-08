package com.mmonge.game.gacha.utils;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class AleatoireUtils {

    public int genererNombreAleatoire() {
        Random random = new Random();
        random.setSeed(new Date().getTime());
        return random.nextInt(100) + 1;
    }
}
