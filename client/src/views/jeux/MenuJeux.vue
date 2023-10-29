<script lang="ts">
import { defineComponent } from "vue";
import { useI18n } from "vue-i18n";
import Taquin from "./Taquin.vue";

export default defineComponent({
  name: "MenuJeux",
  components: {
    Taquin,
  },
  data() {
    return { jeu: "", difficulte: 0, jouer: false, trad: useI18n().t };
  },
  methods: {
    arreterJeu() {
      this.jouer = false;
      this.$emit("actualiserUtilisateur");
    },

    lancerPartie(difficulte: number) {
      this.difficulte = difficulte;
      this.jouer = true;
    },
  },
});
</script>

<template>
  <main class="menu-jeux">
    <div class="menu-jeux-liste">
      <div class="menu-jeux-liste-jeu" @click="jeu = 'Taquin'">
        <img class="menu-jeux-liste-jeu-image" src="../../assets/taquin.png" />
        <span>Taquin</span>
      </div>
      <div class="menu-jeux-liste-jeu" @click="jeu = 'BatailleNavale'">
        <img class="menu-jeux-liste-jeu-image" src="../../assets/taquin.png" />
        <span>WIP</span>
      </div>
    </div>

    <div class="menu-jeux-taquin" v-if="jeu === 'Taquin'">
      <div class="menu-jeux-taquin-jouer" v-if="!jouer">
        <div class="menu-jeux-taquin-jouer-label">
          {{ trad("accueil.jouer") }}
        </div>
        <div class="menu-jeux-taquin-jouer-boutons">
          <button @click="lancerPartie(3)">3x3</button>
          <button @click="lancerPartie(4)">4x4</button>
          <button @click="lancerPartie(5)">5x5</button>
        </div>
      </div>
      <Taquin :dimension="difficulte" v-else @arreter-jeu="arreterJeu()" />
    </div>
  </main>
</template>

<style lang="less">
.menu-jeux {
  display: flex;
  flex-direction: column;
  overflow: hidden;

  &-liste {
    text-align: center;
    display: flex;
    flex-direction: row;
    margin: auto;

    &-jeu {
      cursor: pointer;
      width: 100px;
      height: 100px;

      img {
        width: 60px;
        height: 66px;
      }
    }
  }

  &-taquin {
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
