package com.mmonge.game.gacha.utils;

import com.mmonge.game.gacha.model.enums.RareteEnum;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RareteUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(RareteUtils.class);

    private final AleatoireUtils aleatoireUtils;

    public RareteEnum getRareteAleatoire() {
        RareteEnum rarete = null;
        int nombreAleatoire = aleatoireUtils.genererNombreAleatoire();
        if (nombreAleatoire <= RareteEnum.C.getChance()) {
            rarete = RareteEnum.C;
        } else if (nombreAleatoire <= RareteEnum.S.getChance()) {
            rarete = RareteEnum.S;
        } else if (nombreAleatoire <= RareteEnum.R.getChance()) {
            rarete = RareteEnum.R;
        } else if (nombreAleatoire <= RareteEnum.E.getChance()) {
            rarete = RareteEnum.E;
        } else if (nombreAleatoire <= RareteEnum.L.getChance()) {
            rarete = RareteEnum.L;
        } else if (nombreAleatoire <= RareteEnum.M.getChance()) {
            rarete = RareteEnum.M;
        } else {
            rarete = RareteEnum.F;
        }
        return rarete;
    }
}
