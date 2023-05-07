<script lang="ts">
import { ObjetCollection } from "@/model/models";
import { PropType, defineComponent } from "vue";

export default defineComponent({
  name: "ObjetCollectionComponent",
  props: {
    objCollection: Object as PropType<ObjetCollection>,
    mesObjets: Object as PropType<number[]>,
  },
  methods: {
    getImageSrc() {
      return "data:image/png;base64," + this.objCollection?.image?.image;
    },

    objetDansLaCollection() {
      return this.objCollection
        ? this.mesObjets?.includes(this.objCollection.id)
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
          default:
            couleur = "#ffffff";
            break;
        }
      }
      return couleur;
    },
  },
});
</script>

<template>
  <div class="objet-collection" :style="{ borderColor: getCouleurRarete() }">
    <div class="item">
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
  width: 130px;
  height: 120px;
  border: 7px solid;

  .item {
    display: flex;
    flex-direction: column;

    img {
      margin-top: 5px;
      margin-left: 40px;
      width: 50px;
      height: 58px;
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
