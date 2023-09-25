<script lang="ts">
import { defineComponent } from "vue";
import { useI18n } from "vue-i18n";
import Taquin from "./jeux/Taquin.vue";

export default defineComponent({
  name: "AccueilView",
  components: {
    Taquin,
  },
  data() {
    return { dimension: 0, jouer: false, trad: useI18n().t };
  },
  methods: {
    arreterJeu() {
      this.jouer = false;
      this.$emit("actualiserUtilisateur");
    },

    lancerPartie(dimension: number) {
      this.dimension = dimension;
      this.jouer = true;
    },
  },
});
</script>

<template>
  <main class="accueil">
    <div class="accueil-jeu">
      <div class="accueil-jeu-jouer" v-if="!jouer">
        <div class="accueil-jeu-jouer-label">{{ trad("accueil.jouer") }}</div>
        <div class="accueil-jeu-jouer-boutons">
          <button @click="lancerPartie(3)">3x3</button>
          <button @click="lancerPartie(4)">4x4</button>
          <button @click="lancerPartie(5)">5x5</button>
        </div>
      </div>
      <Taquin :dimension="dimension" v-else @arreter-jeu="arreterJeu()" />
    </div>
  </main>
</template>

<style lang="less">
.accueil {
  display: flex;
  flex-direction: column;
  overflow: hidden;

  &-jeu {
    margin: auto;
    width: 121px;
    height: 134px;
    border: 1px solid;

    &-jouer {
      width: 100%;
      height: 100%;
      border: none;
      background: none;

      &-label {
        margin-top: 5px;
        position: relative;
        text-align: center;
      }

      &-boutons {
        position: relative;
        width: 100%;
        height: 100%;
        vertical-align: middle;
        margin-top: 5%;
        text-align: center;

        button {
          margin: 5px 0px 5px 0px;
          width: 70px;
        }
      }
    }
  }
}
</style>
