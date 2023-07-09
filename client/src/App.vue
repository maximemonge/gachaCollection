<script lang="ts">
import { defineComponent } from "vue";
import ConnexionView from "./views/ConnexionView.vue";
import { Utilisateur } from "./model/models";
import { useI18n } from "vue-i18n";
import {
  LANGAGE_CACHE,
  getLangues,
  setLangageDansCache,
} from "./utils/languesUtils";
import {
  getUtilisateurFromCache,
  removeUtilisateurFromCache,
  setUtilisateurDansCache,
} from "./utils/utilisateurUtils";

export default defineComponent({
  name: "MainView",
  components: {
    ConnexionView,
  },
  data() {
    const i18n = useI18n();
    let utilisateur: Utilisateur = undefined!;
    const langues = getLangues();
    return { utilisateur, langues, i18n, trad: i18n.t };
  },

  mounted() {
    const langueStockee = sessionStorage.getItem(LANGAGE_CACHE);
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
  },
});
</script>

<template class="app">
  <div class="app-header">
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

  <div class="app-body" v-if="getUtilisateurConnecte">
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
          }}<img src="./assets/argent.png"
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
      <router-view :key="$route.path" @perte-monnaie="perteMonnaie" />
    </div>
  </div>
  <ConnexionView v-else @utilisateur-connecte="connecterUtilisateur" />
</template>

<style lang="less">
html {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  overflow: hidden;

  body {
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 100%;

    #app {
      flex-grow: 1;
      display: flex;
      flex-direction: column;
      position: fixed;
      top: 0;
      left: 0;
      bottom: 0;
      right: 0;
      margin: 5px;

      .app {
        &-header {
          position: absolute;
          width: 100%;
          text-align: end;

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
            width: 100%;

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

            nav {
              a {
                font-weight: bold;
                color: #2c3e50;

                &.router-link-exact-active {
                  color: #42b983;
                }
              }
            }
          }

          &-contenu {
            margin-top: 15px;
            overflow: scroll;
            overflow-x: hidden;
          }
        }
      }
    }
  }
}
</style>
