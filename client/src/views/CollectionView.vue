<script lang="ts">
import { PropType, defineComponent } from "vue";
import axios from "axios";
import ObjetCollectionComponent from "../components/ObjetCollectionComponent.vue";
import { ObjetCollection, Utilisateur } from "@/model/models";
import { useRoute } from "vue-router";

export default defineComponent({
  name: "CollectionView",
  components: {
    ObjetCollectionComponent,
  },
  data() {
    let objetCollections: ObjetCollection[] = [];
    let mesObjets: number[] = [];
    const route = useRoute();
    const utilisateurId = route.query.utilisateurId;
    return { objetCollections, mesObjets, utilisateurId };
  },
  mounted() {
    this.getAllObjetCollection();
    this.getMaCollection();
  },
  methods: {
    async getAllObjetCollection() {
      await axios
        .get("http://localhost:3000/objetCollection/all")
        .then((reponse) => {
          this.objetCollections = reponse.data;
        })
        .catch(() => {
          console.log("Échec lors de la récupération des objets de collection");
        });
    },

    async getMaCollection() {
      await axios
        .get(
          "http://localhost:3000/objetCollection/all/user/" + this.utilisateurId
        )
        .then((reponse) => {
          this.mesObjets = reponse.data;
        })
        .catch(() => {
          console.log("Échec lors de la récupération des objets de collection");
        });
    },
  },
});
</script>

<template>
  <div class="collection">
    <div class="espace" v-for="objetCollection in objetCollections">
      <ObjetCollectionComponent
        :obj-collection="objetCollection"
        :mes-objets="mesObjets"
      ></ObjetCollectionComponent>
    </div>
  </div>
</template>

<style lang="less">
.collection {
  flex-grow: 1;
  text-align: center;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  overflow: auto;

  .espace {
    margin-bottom: 40px;
  }
}
</style>
