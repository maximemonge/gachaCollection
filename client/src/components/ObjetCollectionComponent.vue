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
  },
});
</script>

<template>
  <div class="objet-collection">
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
  height: 80px;
  .item {
    display: flex;
    flex-direction: column;

    img {
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
