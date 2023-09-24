<script lang="ts">
import { defineComponent } from "vue";
import { useI18n } from "vue-i18n";
import Puzzle from "./jeux/Puzzle.vue";

export default defineComponent({
  name: "AccueilView",
  components: {
    Puzzle,
  },
  data() {
    return { jouer: false, trad: useI18n().t };
  },
  methods: {
    arreterJeu() {
      this.jouer = false;
      this.$emit("actualiserUtilisateur");
    },
  },
});
</script>

<template>
  <main class="accueil">
    <div class="accueil-jeu">
      <button class="accueil-jeu-jouer" v-if="!jouer" @click="jouer = true">
        {{ trad("accueil.jouer") }}
      </button>
      <Puzzle v-else @arreter-jeu="arreterJeu()" />
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
      cursor: pointer;
    }
  }
}
</style>
