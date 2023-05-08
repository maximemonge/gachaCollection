<script lang="ts">
import { defineComponent } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import { ObjetCollection } from "@/model/models";
import ObjetCollectionComponent from "@/components/ObjetCollectionComponent.vue";

export default defineComponent({
  name: "AccueilView",
});
</script>

<template>
  <div class="accueil"></div>
</template>

<style lang="less"></style>
