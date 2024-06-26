package com.mmonge.game.gacha.web.controller;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.services.ObjetCollectionService;
import com.mmonge.game.gacha.services.UtilisateurCollectionService;
import com.mmonge.game.gacha.services.UtilisateurService;
import com.mmonge.game.gacha.web.controller.cors.ControllerConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/objetCollection")
public class ObjetCollectionController extends ControllerConfig {
    private final ObjetCollectionService objetCollectionService;
    private final UtilisateurCollectionService utilisateurCollectionService;
    private final UtilisateurService utilisateurService;

    public ObjetCollectionController(ObjetCollectionService objetCollectionService, UtilisateurCollectionService utilisateurCollectionService, UtilisateurService utilisateurService) {
        this.objetCollectionService = objetCollectionService;
        this.utilisateurCollectionService = utilisateurCollectionService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<Map<String, List<ObjetCollectionDTO>>> getAllObjetCollection() {
        TreeMap<String, List<ObjetCollectionDTO>> objetsParCategorie = new TreeMap<>(objetCollectionService.findAll().stream().collect(Collectors.groupingBy(ObjetCollectionDTO::categorie)));
        objetsParCategorie.values().forEach(l -> l.sort(Comparator.comparing(ObjetCollectionDTO::rarete)));
        return ResponseEntity.ok(objetsParCategorie);
    }

    @GetMapping(path = "/all/user/{utilisateurId}")
    public @ResponseBody ResponseEntity<List<UtilisateurCollectionDTO>> getAllObjetCollectionByUtilisateurId(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(utilisateurCollectionService.findAllIdsByUtilisateurId(utilisateurId));
    }

    @GetMapping(path = "/obtenir/user/{utilisateurId}/{cout}")
    public @ResponseBody ResponseEntity<ObjetCollectionDTO> obtenirUnObjet(@PathVariable Long utilisateurId, @PathVariable Long cout) {
        ObjetCollectionDTO objetAleatoire = objetCollectionService.obtenirUnObjet();
        utilisateurCollectionService.ajouterObjetDansLaCollection(utilisateurId, objetAleatoire.id());
        utilisateurService.retirerMonnaie(utilisateurId, cout);
        return ResponseEntity.ok(objetAleatoire);
    }

    @GetMapping(path = "/random")
    public @ResponseBody ResponseEntity<ObjetCollectionDTO> getOneObjetCollectionAleatoire() {
        return ResponseEntity.ok(objetCollectionService.obtenirUnObjet());
    }
}
