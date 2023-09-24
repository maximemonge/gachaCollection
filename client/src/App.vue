<script lang="ts">
import { defineComponent } from "vue";
import ConnexionView from "./views/ConnexionView.vue";
import { Utilisateur } from "./model/models";
import { useI18n } from "vue-i18n";
import {
  LANGAGE_CACHE,
  getLangageDansCache,
  getLangues,
  setLangageDansCache,
} from "./utils/languesUtils";
import {
  getUtilisateurFromCache,
  removeUtilisateurFromCache,
  setUtilisateurDansCache,
} from "./utils/utilisateurUtils";

import SwitchComponent from "./components/SwitchComponent.vue";
import { ThemeEnum, getThemeDansCache } from "./utils/themeUtils";
export default defineComponent({
  name: "MainView",
  components: {
    ConnexionView,
    SwitchComponent,
  },
  data() {
    const i18n = useI18n();
    let utilisateur: Utilisateur = undefined!;
    let theme: any = ThemeEnum.CLAIR;
    const langues = getLangues();
    return { utilisateur, langues, i18n, trad: i18n.t, theme };
  },

  mounted() {
    const langueStockee: string = getLangageDansCache();
    if (langueStockee) {
      this.i18n.locale = langueStockee;
    }
  },

  computed: {
    getUtilisateurConnecte(): Utilisateur {
      if (!this.utilisateur) {
        this.utilisateur = getUtilisateurFromCache();
      }
      return this.utilisateur;
    },

    getTheme(): ThemeEnum {
      const themeCache: ThemeEnum = getThemeDansCache();
      if (themeCache) {
        this.theme = themeCache;
      }
      return this.theme;
    },
  },

  watch: {
    "i18n.locale": (langueChoisie: string) => {
      setLangageDansCache(langueChoisie);
    },
  },

  methods: {
    connecterUtilisateur(utilisateur: Utilisateur) {
      this.utilisateur = utilisateur;
      setUtilisateurDansCache(this.utilisateur);
      this.$router.push("/");
    },

    deconnecterUtilisateur() {
      this.utilisateur = undefined!;
      removeUtilisateurFromCache();
      this.$router.push("/");
    },

    perteMonnaie(cout: number) {
      this.utilisateur.monnaie -= cout;
      setUtilisateurDansCache(this.utilisateur);
    },

    changerTheme(nouveauTheme: ThemeEnum) {
      this.theme = nouveauTheme;
    },

    actualiserDonneeUtilisateur() {
      this.utilisateur = getUtilisateurFromCache();
    },
  },
});
</script>

<template class="app">
  <div
    class="app-header"
    :class="[theme === 'SOMBRE' ? 'theme-sombre' : 'theme-clair']"
  >
    <SwitchComponent
      :theme="getTheme"
      @update:changer-theme="changerTheme"
    ></SwitchComponent>
    <select class="app-header-langues" v-model="i18n.locale">
      <option v-for="langue in langues" :value="langue.id">
        {{ langue.libelle }}
      </option>
    </select>
    <button
      v-if="getUtilisateurConnecte"
      class="app-header-deconnexion"
      type="button"
      @click="deconnecterUtilisateur"
    >
      {{ trad("header.deconnexion") }}
    </button>
  </div>

  <div
    class="app-body"
    :class="[theme === 'SOMBRE' ? 'theme-sombre' : 'theme-clair']"
    v-if="getUtilisateurConnecte"
  >
    <div class="app-body-informations-utilisateur">
      <div class="app-body-informations-utilisateur-pseudo">
        <span>{{
          trad("header.bienvenue.user", {
            identifiant: utilisateur.identifiant,
          })
        }}</span>
      </div>
      <div
        class="app-body-informations-utilisateur-monnaie"
        :key="utilisateur.monnaie"
      >
        <span
          >{{ trad("header.argent") }} : {{ utilisateur.monnaie
          }}<img class="inverser-couleurs" src="./assets/argent.png"
        /></span>
      </div>
    </div>
    <div class="app-body-menu">
      <nav>
        <router-link to="/">{{ trad("header.menu.accueil") }}</router-link>
        |
        <router-link
          :to="{
            path: '/gacha',
          }"
          >{{ trad("header.menu.gacha") }}</router-link
        >
        |
        <router-link
          :to="{
            path: '/collection',
            query: { utilisateurId: utilisateur.id },
          }"
          >{{ trad("header.menu.macollection") }}</router-link
        >
        |
        <router-link to="/profil">{{ trad("header.menu.profil") }}</router-link>
      </nav>
    </div>
    <div class="app-body-contenu">
      <router-view
        :key="$route.path"
        @perte-monnaie="perteMonnaie"
        @actualiser-utilisateur="actualiserDonneeUtilisateur"
      />
    </div>
  </div>
  <ConnexionView
    v-else
    @utilisateur-connecte="connecterUtilisateur"
    :theme="getTheme"
  />
</template>

<style lang="less">
@import "common/less/theme.less";
#app {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;

  .app {
    &-header {
      position: absolute;
      width: 100%;
      text-align: end;
      height: 100%;

      &-deconnexion {
        margin-left: 5px;
      }
    }

    &-body {
      height: 100%;
      display: flex;
      flex-direction: column;

      &-informations-utilisateur {
        flex-grow: 0;
        z-index: 1;
        width: 50%;

        &-pseudo {
          display: flex;
          flex-direction: row;
          width: 100%;

          span {
            flex-grow: 1;
          }
        }

        &-monnaie {
          img {
            width: 12px;
            height: 12px;
            margin-top: 3px;
            margin-left: 3px;
          }
        }
      }

      &-menu {
        position: absolute;
        width: 100%;
        text-align: center;
        margin-top: 25px;
        z-index: 1;

        nav {
          a {
            font-weight: bold;
          }
        }
      }

      &-contenu {
        margin-top: 15px;
        overflow: auto;
        overflow-x: hidden;
        z-index: 1;
      }
    }
  }
}
</style>
