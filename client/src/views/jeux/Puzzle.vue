<script lang="ts">
import { PropType, defineComponent } from "vue";
import { useI18n } from "vue-i18n";
import axios from "axios";
import { ObjetCollection, PiecePuzzle } from "@/model/models";
import {
  getUtilisateurFromCache,
  setUtilisateurDansCache,
} from "@/utils/utilisateurUtils";

export default defineComponent({
  name: "Puzzle",
  data() {
    let objetCollection: ObjetCollection = undefined!;
    const piecesPuzzle: PiecePuzzle[] = [];
    return {
      objetCollection,
      piecesPuzzle,
      victoire: false,
      trad: useI18n().t,
    };
  },
  props: {
    dimension: Number as PropType<Number>,
  },
  mounted() {
    this.initPiecesPuzzle();
    this.getObjetAleatoire();
  },
  methods: {
    async getObjetAleatoire() {
      await axios
        .get("http://localhost:3000/objetCollection/random")
        .then((reponse) => {
          this.objetCollection = reponse.data;
        })
        .catch(() => {
          console.log(
            "Échec lors de la récupération d'un objet de collection aléatoire"
          );
        });
    },

    getDimension(): number {
      return this.dimension?.valueOf() || 0;
    },

    getImageSrc() {
      return "data:image/png;base64," + this.objetCollection?.image?.image;
    },

    getImageSrcUrl() {
      return "url(" + this.getImageSrc() + ")";
    },

    initPiecesPuzzle() {
      let compteur = 0;
      for (let y = 0; y < this.getDimension(); y++) {
        for (let x = 0; x < this.getDimension(); x++) {
          this.piecesPuzzle.push(
            this.creerPiecePuzzle(
              -x * (121 / this.getDimension()),
              -y * (134 / this.getDimension()),
              Math.random(),
              compteur
            )
          );
          compteur++;
        }
      }
      this.piecesPuzzle.sort((a, b) => (a.ordre < b.ordre ? -1 : 1));
      this.piecesPuzzle[0].vide = true;
    },

    creerPiecePuzzle(
      x: number,
      y: number,
      ordre: number,
      index: number
    ): PiecePuzzle {
      const posX = x + "px";
      const posY = y + "px";
      return { posX, posY, ordre, vide: false, index };
    },

    getTaillePiece() {
      return 100 / this.getDimension() + "%";
    },

    getBackgroundPosition(piece: PiecePuzzle) {
      return piece.posX + " " + piece.posY;
    },

    getPositionPieceVide() {
      return this.piecesPuzzle.map((piece) => piece.vide).indexOf(true);
    },

    deplacerPiece(piece: PiecePuzzle, index: number) {
      const indexPositionVide = this.getPositionPieceVide();
      this.piecesPuzzle[index] = this.piecesPuzzle[indexPositionVide];
      this.piecesPuzzle[indexPositionVide] = piece;

      this.victoire = this.verifierVictoire();
    },

    isDeplacementAutorise(index: number) {
      const indexPositionVide = this.getPositionPieceVide();
      return index + this.getDimension() == indexPositionVide ||
        index - this.getDimension() == indexPositionVide ||
        (index + 1 == indexPositionVide &&
          index % this.getDimension() != this.getDimension() - 1) ||
        (index - 1 == indexPositionVide && index % this.getDimension() != 0)
        ? "all"
        : "none";
    },

    verifierVictoire() {
      return (
        this.piecesPuzzle.find((p) => p != this.piecesPuzzle[p.index]) ===
        undefined
      );
    },

    async gagnerArgent() {
      await axios
        .post("http://localhost:3000/user/monnaie", {
          id: getUtilisateurFromCache().id,
          monnaie: this.getDimension(),
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
  <main v-if="objetCollection && piecesPuzzle" class="puzzle">
    <canvas
      v-if="!victoire"
      v-for="(piece, index) of piecesPuzzle"
      class="puzzle-piece"
      :class="[index > 2 ? 'supprimer-marge' : '']"
      :style="{
        width: getTaillePiece(),
        height: getTaillePiece(),
        background: piece.vide ? 'grey' : getImageSrcUrl(),
        backgroundImage: piece.vide ? '' : getImageSrcUrl(),
        backgroundPosition: getBackgroundPosition(piece),
        pointerEvents: isDeplacementAutorise(index),
      }"
      @click="deplacerPiece(piece, index)"
    ></canvas>
    <div class="puzzle-termine" v-else>
      <button @click="gagnerArgent()">Obtenir de l'argent</button>
      <img :src="getImageSrc()" />
    </div>
  </main>
</template>

<style lang="less">
.puzzle {
  text-align: justify;
  flex-wrap: wrap;
  width: 100%;
  height: 100%;
  background: white !important;

  &-piece {
    cursor: pointer;
  }

  .supprimer-marge {
    margin-top: -4px;
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
      margin: 50px 11px 50px 11px;
      cursor: pointer;
    }
  }
}
</style>
