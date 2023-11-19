<script lang="ts">
import { PropType, defineComponent } from "vue";
import { DedaleColoreTerrain } from "@/model/models";
import {
  getUtilisateurFromCache,
  setUtilisateurDansCache,
} from "@/utils/utilisateurUtils";
import axios from "axios";

export default defineComponent({
  name: "DedaleColore",
  data() {
    return {
      terrain: this.initialiserTerrain(),
      posX: 5,
      posY: 5,
      checkpoint: [5, 5],
      tresors: 4,
    };
  },
  methods: {
    initialiserTerrain(): DedaleColoreTerrain[][] {
      return [
        [
          DedaleColoreTerrain.TRESOR,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.VIDE,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.TRESOR,
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.VIDE,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          DedaleColoreTerrain.VIDE,
          DedaleColoreTerrain.VIDE,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.CHECKPOINT,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.VIDE,
          DedaleColoreTerrain.VIDE,
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.VIDE,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
        ],
        [
          DedaleColoreTerrain.TRESOR,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.VIDE,
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          this.terrainAleatoire(),
          DedaleColoreTerrain.TRESOR,
        ],
      ];
    },

    terrainAleatoire(): DedaleColoreTerrain {
      const couleurs = [
        DedaleColoreTerrain.JAUNE,
        DedaleColoreTerrain.BLEU,
        DedaleColoreTerrain.ROUGE,
        DedaleColoreTerrain.VERT,
      ];
      return couleurs[Math.floor(Math.random() * couleurs.length)];
    },

    couleurBackground(couleur: DedaleColoreTerrain): string {
      let couleurCase: string = "";
      switch (couleur) {
        case DedaleColoreTerrain.JAUNE:
          couleurCase = "yellow";
          break;
        case DedaleColoreTerrain.ROUGE:
          couleurCase = "red";
          break;
        case DedaleColoreTerrain.VERT:
          couleurCase = "limegreen";
          break;
        case DedaleColoreTerrain.BLEU:
          couleurCase = "blue";
          break;
        case DedaleColoreTerrain.TRESOR:
          couleurCase = "white";
          break;
        case DedaleColoreTerrain.VIDE:
          couleurCase = "black";
          break;
        case DedaleColoreTerrain.CHECKPOINT:
          couleurCase = "white";
          break;
      }
      return couleurCase;
    },

    isPositionJoueur(x: number, y: number): boolean {
      return this.posX === x && this.posY === y;
    },

    deplacer(index: number, x: number, y: number): boolean {
      return (
        index >= 0 &&
        x >= 0 &&
        y >= 0 &&
        index < this.terrain.length &&
        x < this.terrain.length &&
        y < this.terrain.length &&
        this.deplacementAutorise(this.terrain[y][x])
      );
    },

    deplacementAutorise(cellule: DedaleColoreTerrain): boolean {
      return !(cellule === DedaleColoreTerrain.VIDE || cellule === undefined);
    },

    appliquerCouleur(position: string, direction: number) {
      switch (this.terrain[this.posY][this.posX]) {
        case DedaleColoreTerrain.CHECKPOINT:
          this.checkpoint = [this.posX, this.posY];
          break;
        case DedaleColoreTerrain.JAUNE:
          this.appliquerCouleurJaune(position, direction);
          break;
        case DedaleColoreTerrain.ROUGE:
          this.appliquerCouleurRouge();
          break;
        case DedaleColoreTerrain.VERT:
          this.appliquerCouleurVerte(position, direction);
          break;
        case DedaleColoreTerrain.BLEU:
          this.appliquerCouleurBleue();
          break;
        case DedaleColoreTerrain.TRESOR:
          this.terrain[this.posY][this.posX] = DedaleColoreTerrain.CHECKPOINT;
          this.checkpoint = [this.posX, this.posY];
          this.tresors -= 1;
          break;
      }
    },

    permuterCouleur(couleur: DedaleColoreTerrain): DedaleColoreTerrain {
      if (couleur === DedaleColoreTerrain.JAUNE) {
        couleur = DedaleColoreTerrain.ROUGE;
      } else if (couleur === DedaleColoreTerrain.ROUGE) {
        couleur = DedaleColoreTerrain.VERT;
      } else if (couleur === DedaleColoreTerrain.VERT) {
        couleur = DedaleColoreTerrain.BLEU;
      } else if (couleur === DedaleColoreTerrain.BLEU) {
        couleur = DedaleColoreTerrain.JAUNE;
      }
      return couleur;
    },

    appliquerCouleurRouge() {
      this.terrain[this.posY][this.posX] = DedaleColoreTerrain.VERT;
      if (this.deplacer(this.posY, this.posX + 1, this.posY)) {
        this.terrain[this.posY][this.posX + 1] = this.permuterCouleur(
          this.terrain[this.posY][this.posX + 1]
        );
      }
      if (this.deplacer(this.posY, this.posX - 1, this.posY)) {
        this.terrain[this.posY][this.posX - 1] = this.permuterCouleur(
          this.terrain[this.posY][this.posX - 1]
        );
      }
      if (this.deplacer(this.posY, this.posX, this.posY + 1)) {
        this.terrain[this.posY + 1][this.posX] = this.permuterCouleur(
          this.terrain[this.posY + 1][this.posX]
        );
      }
      if (this.deplacer(this.posY, this.posX, this.posY - 1)) {
        this.terrain[this.posY - 1][this.posX] = this.permuterCouleur(
          this.terrain[this.posY - 1][this.posX]
        );
      }
    },

    appliquerCouleurBleue() {
      this.terrain[this.posY][this.posX] = DedaleColoreTerrain.ROUGE;
      this.posX = this.checkpoint[0];
      this.posY = this.checkpoint[1];
    },

    appliquerCouleurVerte(position: string, direction: number) {
      this.terrain[this.posY][this.posX] = DedaleColoreTerrain.JAUNE;
      if (
        position === "x" &&
        this.deplacer(this.posX, this.posX - direction, this.posY)
      ) {
        this.posX -= direction;
        this.appliquerCouleur(position, direction);
      } else if (
        position === "y" &&
        this.deplacer(this.posX, this.posX, this.posY - direction)
      ) {
        this.posY -= direction;
        this.appliquerCouleur(position, direction);
      }
    },

    appliquerCouleurJaune(position: string, direction: number) {
      this.terrain[this.posY][this.posX] = DedaleColoreTerrain.ROUGE;
      if (
        position === "x" &&
        this.deplacer(this.posX, this.posX + direction, this.posY)
      ) {
        this.posX += direction;
        this.appliquerCouleur(position, direction);
      } else if (
        position === "y" &&
        this.deplacer(this.posX, this.posX, this.posY + direction)
      ) {
        this.posY += direction;
        this.appliquerCouleur(position, direction);
      }
    },

    onPressZ() {
      if (this.deplacer(this.posY, this.posX, this.posY - 1)) {
        this.posY -= 1;
        this.appliquerCouleur("y", -1);
      }
    },
    onPressQ() {
      if (this.deplacer(this.posX, this.posX - 1, this.posY)) {
        this.posX -= 1;
        this.appliquerCouleur("x", -1);
      }
    },

    onPressS() {
      if (this.deplacer(this.posY, this.posX, this.posY + 1)) {
        this.posY += 1;
        this.appliquerCouleur("y", 1);
      }
    },
    onPressD() {
      if (this.deplacer(this.posX, this.posX + 1, this.posY)) {
        this.posX += 1;
        this.appliquerCouleur("x", 1);
      }
    },

    async gagnerArgent() {
      await axios
        .post("http://localhost:3000/user/monnaie", {
          id: getUtilisateurFromCache().id,
          monnaie: 5,
        })
        .then((reponse) => {
          setUtilisateurDansCache(reponse.data);
          this.$emit("arreterJeu");
        });
    },
  },
});
</script>

<template>
  <main
    class="dedaleColore"
    tabindex="1"
    @keypress.z="onPressZ()"
    @keypress.q="onPressQ()"
    @keypress.s="onPressS()"
    @keypress.d="onPressD()"
  >
    <div class="ligne" v-for="(ligne, y) in terrain" v-if="tresors > 0">
      <template v-for="(couleur, x) in ligne">
        <div class="case" :style="{ background: couleurBackground(couleur) }">
          <font-awesome-icon
            class="icone"
            icon="fa-solid fa-dog"
            v-if="isPositionJoueur(x, y)"
          />
          <font-awesome-icon
            class="icone"
            icon="fa-solid fa-gem"
            v-if="couleur === 'TRESOR'"
          />
        </div>
      </template>
    </div>
    <div class="dedaleColore-termine" v-else>
      <button @click="gagnerArgent()">Obtenir de l'argent</button>
    </div>
  </main>
</template>

<style lang="less">
.dedaleColore {
  .ligne {
    width: 100%;
    height: 15px;
    display: flex;
    flex-direction: row;
    .case {
      border: 0.5px black solid;
      width: 15px;
      height: 15px;

      .icone {
        width: 13px;
        height: 13px;
        font-weight: bold;
        margin: 0px 0px 2px 0.5px;
      }
    }
  }

  &-termine {
    width: 100%;
    height: 100%;
    img {
      width: 121px;
      height: 134px;
    }

    button {
      position: absolute;
      width: 100px;
      background: rgba(225, 202, 202, 0.744);
      margin: 50px 0px 0px 30px;
      cursor: pointer;
    }
  }
}
</style>
