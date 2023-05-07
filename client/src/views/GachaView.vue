<script lang="ts">
import { defineComponent } from "vue";
import axios from "axios";

export default defineComponent({
  name: "GachaView",
  methods: {
    async obtenirUnObjet() {
      await axios
        .get("http://localhost:3000/objetCollection/obtenir")
        .then((reponse) => {
          reponse.data;
        })
        .catch(() => {
          console.log(
            "Échec lors de la récupération d'un objet de collection aléatoire"
          );
        });
    },
  },
});
</script>

<template>
  <div class="gacha">
    <button type="button" @click="obtenirUnObjet">Obtenir un objet</button>
  </div>
</template>

<style lang="less">
.gacha {
  text-align: center;
}
</style>
