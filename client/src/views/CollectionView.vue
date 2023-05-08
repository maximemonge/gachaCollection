<script lang="ts">
import { PropType, defineComponent } from "vue";
import axios from "axios";
import ObjetCollectionComponent from "../components/ObjetCollectionComponent.vue";
import { ObjetCollection, UtilisateurCollection } from "@/model/models";
import { useRoute } from "vue-router";

export default defineComponent({
  name: "CollectionView",
  components: {
    ObjetCollectionComponent,
  },
  data() {
    let objetCollectionParCategories: Map<string, ObjetCollection[]> =
      new Map();
    let mesObjets: UtilisateurCollection[] = [];
    const route = useRoute();
    const utilisateurId = route.query.utilisateurId;
    return { objetCollectionParCategories, mesObjets, utilisateurId };
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
          this.objetCollectionParCategories = reponse.data;
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

    castObjetEnObjetCollection(objet: any) {
      const objetCast: ObjetCollection = objet;
      return objetCast;
    },
  },
});
</script>

<template>
  <div class="collection">
    <div class="espace">
      <template v-for="(value, key) in objetCollectionParCategories">
        <div class="objetsParCategorie espace">
          <span class="categorie">{{ key }}</span>
          <div class="objets">
            <ObjetCollectionComponent
              class="marge"
              v-for="objet in value"
              :obj-collection="castObjetEnObjetCollection(objet)"
              :mes-objets="mesObjets"
              :afficherQuantite="true"
            ></ObjetCollectionComponent>
          </div></div
      ></template>
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
    margin-bottom: 10px;
    margin-left: 10px;
    margin-right: 10px;

    .objetsParCategorie {
      display: flex;
      flex-direction: column;

      .categorie {
        font-size: 30px;
        margin-bottom: 30px;
        margin-top: 30px;
        text-align: left;
      }

      .objets {
        display: inline-flex;
        flex-flow: wrap;

        .marge {
          margin-bottom: 10px;
          margin-left: 10px;
          margin-right: 10px;
        }
      }
    }
  }
}
</style>
