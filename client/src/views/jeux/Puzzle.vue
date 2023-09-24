<script lang="ts">
import { defineComponent } from "vue";
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

    getImageSrc() {
      return "data:image/png;base64," + this.objetCollection?.image?.image;
    },

    getImageSrcUrl() {
      return "url(" + this.getImageSrc() + ")";
    },

    initPiecesPuzzle() {
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("0px", "0px", Math.random(), 0)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("80.66px", "0px", Math.random(), 1)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("40.33px", "0px", Math.random(), 2)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("0px", "89.33px", Math.random(), 3)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("80.66px", "89.33px", Math.random(), 4)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("40.33px", "89.33px", Math.random(), 5)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("0px", "44.66px", Math.random(), 6)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("80.66px", "44.66px", Math.random(), 7)
      );
      this.piecesPuzzle.push(
        this.creerPiecePuzzle("40.33px", "44.66px", Math.random(), 8)
      );
      this.piecesPuzzle.sort((a, b) => (a.ordre < b.ordre ? -1 : 1));
      this.piecesPuzzle[0].vide = true;
    },

    creerPiecePuzzle(
      posX: string,
      posY: string,
      ordre: number,
      index: number
    ): PiecePuzzle {
      return { posX, posY, ordre, vide: false, index };
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
      return index + 3 == indexPositionVide ||
        index - 3 == indexPositionVide ||
        index + 1 == indexPositionVide ||
        index - 1 == indexPositionVide
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
  <main v-if="objetCollection && piecesPuzzle" class="puzzle">
    <canvas
      v-if="!victoire"
      v-for="(piece, index) of piecesPuzzle"
      class="puzzle-piece"
      :class="[index > 2 ? 'supprimer-marge' : '']"
      :style="{
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
    width: 33.33%;
    height: 33.33%;
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
