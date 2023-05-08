<script lang="ts">
import { defineComponent } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import { ObjetCollection } from "@/model/models";
import ObjetCollectionComponent from "@/components/ObjetCollectionComponent.vue";

export default defineComponent({
  name: "GachaView",
  data() {
    let objetCollection: ObjetCollection = undefined!;
    const route = useRoute();
    const utilisateurId = route.query.utilisateurId;
    return { objetCollection, utilisateurId };
  },
  methods: {
    async obtenirUnObjet() {
      await axios
        .get(
          "http://localhost:3000/objetCollection/obtenir/user/" +
            this.utilisateurId
        )
        .then((reponse) => {
          this.objetCollection = reponse.data;
        })
        .catch(() => {
          console.log(
            "Échec lors de la récupération d'un objet de collection aléatoire"
          );
        });
    },
  },
  components: { ObjetCollectionComponent },
});
</script>

<template>
  <div class="gacha">
    <button class="boutonObtention" type="button" @click="obtenirUnObjet">
      Obtenir un objet
    </button>
    <div v-if="objetCollection" class="obtention">
      <ObjetCollectionComponent
        :obj-collection="objetCollection"
        :mes-objets="[objetCollection.id]"
      ></ObjetCollectionComponent>
    </div>
  </div>
</template>

<style lang="less">
.gacha {
  text-align: center;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .boutonObtention {
    width: 150px;
  }

  .obtention {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 15px;
  }
}
</style>
