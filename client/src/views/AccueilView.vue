<script lang="ts">
import { defineComponent } from "vue";
import { useI18n } from "vue-i18n";
import MenuJeux from "./jeux/MenuJeux.vue";

export default defineComponent({
  name: "AccueilView",
  components: {
    MenuJeux,
  },
  methods: {
    actualiserUtilisateur() {
      this.$emit("actualiserUtilisateur");
    },
  },
});
</script>

<template>
  <main class="accueil">
    <MenuJeux @actualiser-utilisateur="actualiserUtilisateur" />
  </main>
</template>

<style lang="less">
.accueil {
  display: flex;
  flex-direction: column;
  overflow: hidden;
}
</style>
