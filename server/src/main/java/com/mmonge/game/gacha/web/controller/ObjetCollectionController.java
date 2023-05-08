package com.mmonge.game.gacha.web.controller;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.services.ObjetCollectionService;
import com.mmonge.game.gacha.services.UtilisateurCollectionService;
import com.mmonge.game.gacha.web.controller.cors.ControllerConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/objetCollection")
@AllArgsConstructor
public class ObjetCollectionController extends ControllerConfig {
    private ObjetCollectionService objetCollectionService;
    private UtilisateurCollectionService utilisateurCollectionService;

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<Map<String, List<ObjetCollectionDTO>>> getAllObjetCollection() {
        TreeMap<String, List<ObjetCollectionDTO>> objetsParCategorie = new TreeMap<>(objetCollectionService.findAll().stream().collect(Collectors.groupingBy(ObjetCollectionDTO::getCategorie)));
        objetsParCategorie.values().forEach(l -> l.sort(Comparator.comparing(ObjetCollectionDTO::getRarete)));
        return ResponseEntity.ok(objetsParCategorie);
    }

    @GetMapping(path = "/all/user/{utilisateurId}")
    public @ResponseBody ResponseEntity<List<UtilisateurCollectionDTO>> getAllObjetCollectionByUtilisateurId(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(utilisateurCollectionService.findAllIdsByUtilisateurId(utilisateurId));
    }

    @GetMapping(path = "/obtenir/user/{utilisateurId}")
    public @ResponseBody ResponseEntity<ObjetCollectionDTO> obtenirUnObjet(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(objetCollectionService.obtenirUnObjet(utilisateurId));
    }
}
