<script lang="ts">
import { defineComponent } from "vue";
import ConnexionView from "./views/ConnexionView.vue";
import { Utilisateur } from "./model/models";

export default defineComponent({
  name: "MainView",
  components: {
    ConnexionView,
  },
  data() {
    let utilisateur: Utilisateur = undefined!;
    return { utilisateur };
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
        <span class="bienvenue">Bienvenue {{ utilisateur.identifiant }} !</span>
        <nav>
          <router-link to="/">Accueil</router-link>
          |
          <router-link
            :to="{
              path: '/gacha',
              query: {
                utilisateurId: utilisateur.id,
                utilisateurMonnaie: utilisateur.monnaie,
              },
            }"
            >Gacha</router-link
          >
          |
          <router-link
            :to="{
              path: '/collection',
              query: { utilisateurId: utilisateur.id },
            }"
            >Ma collection</router-link
          >
          |
          <router-link to="/profil">Profil</router-link>
        </nav>
        <button
          class="deconnexion"
          type="button"
          @click="deconnecterUtilisateur"
        >
          Déconnexion
        </button>
      </div>
      <div class="monnaie" :key="utilisateur.monnaie">
        <span>Monnaie : {{ utilisateur.monnaie }}</span
        ><img src="./assets/argent.png" />
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
              position: absolute;
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
