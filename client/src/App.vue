<script lang="ts">
import { defineComponent } from "vue";
import ConnexionView from "./views/ConnexionView.vue";
import { Utilisateur } from "./model/models";
import { useI18n } from "vue-i18n";
import { getLangues } from "./traductions/langues";

export default defineComponent({
  name: "MainView",
  components: {
    ConnexionView,
  },
  data() {
    const i18n = useI18n();
    const trad = i18n.t;
    let utilisateur: Utilisateur = undefined!;
    const langues = getLangues();
    return { utilisateur, langues, i18n, trad };
  },

  methods: {
    connecterUtilisateur(utilisateur: Utilisateur) {
      this.utilisateur = utilisateur;
      this.$router.push("/");
    },

    deconnecterUtilisateur() {
      this.utilisateur = undefined!;
      this.$router.push("/");
    },

    perteMonnaie(cout: number) {
      this.utilisateur.monnaie -= cout;
    },
  },
});
</script>

<template>
  <div class="menu" v-if="utilisateur">
    <div class="bandeau">
      <div class="titre">
        <span class="bienvenue">{{
          trad("header.bienvenue.user", {
            identifiant: utilisateur.identifiant,
          })
        }}</span>
        <nav>
          <router-link to="/">{{ trad("header.menu.accueil") }}</router-link>
          |
          <router-link
            :to="{
              path: '/gacha',
              query: {
                utilisateurId: utilisateur.id,
                utilisateurMonnaie: utilisateur.monnaie,
              },
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
          <router-link to="/profil">{{
            trad("header.menu.profil")
          }}</router-link>
        </nav>
        <select class="langues" v-model="i18n.locale">
          <option v-for="langue in langues" :value="langue.id">
            {{ langue.libelle }}
          </option>
        </select>
        <button
          class="deconnexion"
          type="button"
          @click="deconnecterUtilisateur"
        >
          {{ trad("header.deconnexion") }}
        </button>
      </div>
      <div class="monnaie" :key="utilisateur.monnaie">
        <span
          >{{ trad("header.argent") }} : {{ utilisateur.monnaie
          }}<img src="./assets/argent.png"
        /></span>
      </div>
    </div>
    <div class="corps">
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
      height: 100%;
      display: flex;
      flex-direction: column;
      position: fixed;
      top: 0;
      left: 0;
      bottom: 0;
      right: 0;
      margin: 5px;

      .menu {
        height: 100%;
        display: flex;
        flex-direction: column;

        .bandeau {
          flex-grow: 0;
          width: 100%;

          .titre {
            display: flex;
            flex-direction: row;
            width: 100%;

            .langues {
              margin-right: 5px;
            }

            .bienvenue {
              flex-grow: 1;
            }

            nav {
              flex-grow: 1;

              a {
                font-weight: bold;
                color: #2c3e50;

                &.router-link-exact-active {
                  color: #42b983;
                }
              }
            }
          }

          .monnaie {
            img {
              width: 12px;
              height: 12px;
              margin-top: 3px;
              margin-left: 3px;
            }
          }
        }

        .corps {
          overflow: auto;
        }
      }
    }
  }
}
</style>
