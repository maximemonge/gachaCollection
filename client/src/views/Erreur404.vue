<script lang="ts">
import { defineComponent } from "vue";
import { useI18n } from "vue-i18n";

export default defineComponent({
  name: "Erreur404",
  data() {
    return { trad: useI18n().t };
  },
});
</script>

<template>
  <main class="erreur404">
    <span>{{ trad("404.erreur") }}</span>
  </main>
</template>

<style lang="less">
.erreur404 {
  text-align: center;
}
</style>
