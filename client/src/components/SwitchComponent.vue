<script lang="ts">
import { ThemeEnum, setThemeDansCache } from "@/utils/themeUtils";
import { PropType, defineComponent } from "vue";

export default defineComponent({
  name: "SwitchComponent",
  props: {
    libelleGauche: String as PropType<String>,
    libelleDroite: String as PropType<String>,
    theme: ThemeEnum as unknown as PropType<ThemeEnum>,
  },
  data() {
    return { valeur: this.theme == ThemeEnum.CLAIR };
  },
  emits: ["update:changer-theme"],
  methods: {
    changerTheme() {
      const nouveauTheme =
        this.theme == ThemeEnum.CLAIR ? ThemeEnum.SOMBRE : ThemeEnum.CLAIR;
      setThemeDansCache(nouveauTheme);
      return nouveauTheme;
    },
  },
});
</script>

<template>
  <div class="switch-component">
    <span class="switch-component-libelle">{{ libelleGauche }}</span>
    <label class="switch-component-toggle">
      <input
        class="switch-component-toggle-checkbox"
        type="checkbox"
        v-model="valeur"
        @click="$emit('update:changer-theme', changerTheme())" />
      <div class="switch-component-toggle-switch"></div
    ></label>
    <span class="switch-component-libelle">{{ libelleDroite }}</span>
  </div>
</template>

<style lang="less">
.switch-component {
  display: inline-block;

  &-libelle {
    pointer-events: none;
    margin-left: 5px;
    margin-right: 5px;
    user-select: none;
  }

  &-toggle {
    &-switch:before,
    &-switch:after {
      content: "";
    }

    // background gauche
    &-switch {
      display: inline-block;
      background: #ffffff;
      border-radius: 16px;
      width: 29px;
      height: 16px;
      position: relative;
      vertical-align: middle;
      cursor: pointer;
    }

    // point gauche
    &-switch:before {
      background: #000000;
      border-radius: 50%;
      width: 12px;
      height: 12px;
      position: absolute;
      top: 2px;
      left: 2px;
    }

    // background droite
    &-checkbox:checked + &-switch {
      background: #000000;
    }

    // point droite
    &-checkbox:checked + &-switch:before {
      transform: translateX(13px);
      background-color: #ffffff;
    }

    &-checkbox {
      display: none;
    }
  }
}
</style>
