<script lang="ts">
import { ObjetCollection, UtilisateurCollection } from "@/model/models";
import { PropType, defineComponent } from "vue";

export default defineComponent({
  name: "ObjetCollectionComponent",
  props: {
    objCollection: Object as PropType<ObjetCollection>,
    mesObjets: Object as PropType<UtilisateurCollection[]>,
    afficherQuantite: Boolean as PropType<boolean>,
  },
  data() {
    const quantite: number = this.getQuantite();
    return { quantite };
  },
  methods: {
    getImageSrc() {
      return "data:image/png;base64," + this.objCollection?.image?.image;
    },

    objetDansLaCollection() {
      return this.objCollection
        ? this.mesObjets
            ?.map((obj) => {
              return obj.objetCollectionId;
            })
            .includes(this.objCollection.id)
        : false;
    },

    getCouleurRarete() {
      let couleur = "#ffffff";
      if (this.objetDansLaCollection()) {
        switch (this.objCollection?.rarete) {
          case "C":
            couleur = "#dedede";
            break;
          case "S":
            couleur = "#05dd11";
            break;
          case "R":
            couleur = "#00ccff";
            break;
          case "E":
            couleur = "#7506b5";
            break;
          case "L":
            couleur = "#ffba00";
            break;
          case "M":
            couleur = "#a50808";
            break;
          case "F":
            couleur = "#000000";
            break;
          default:
            couleur = "#ffffff";
            break;
        }
      }
      return couleur;
    },

    getQuantite() {
      return this.mesObjets?.filter(
        (obj) => this.objCollection?.id === obj.objetCollectionId
      )[0]?.quantite;
    },
  },
});
</script>

<template>
  <div class="objet-collection" :style="{ borderColor: getCouleurRarete() }">
    <div class="item">
      <span
        :class="[quantite && afficherQuantite ? '' : 'cache']"
        class="quantite"
        >x{{ quantite }}</span
      >
      <img
        :class="[objetDansLaCollection() ? '' : 'desactive']"
        :src="getImageSrc()"
      />
      <span>{{ objCollection?.code }}</span>
    </div>
  </div>
</template>

<style lang="less">
.objet-collection {
  width: 200px;
  height: 200px;
  border: 7px solid;

  .item {
    display: flex;
    flex-direction: column;

    .cache {
      visibility: hidden;
    }

    .quantite {
      margin-left: 10px;
      text-align: initial;
      float: left;
    }

    img {
      margin-top: 5px;
      margin-left: 40px;
      width: 121px;
      height: 134px;
    }

    span {
      margin-top: 5px;
    }

    .desactive {
      filter: brightness(0);
    }
  }
}
</style>
