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
    },

    deconnecterUtilisateur() {
      this.utilisateur = undefined!;
      this.$router.push("/");
    },
  },
});
</script>

<template>
  <div class="menu" v-if="utilisateur">
    <div class="bandeau">
      <span class="titre">Bienvenue {{ utilisateur.identifiant }} !</span>
      <button class="deconnexion" type="button" @click="deconnecterUtilisateur">
        Déconnexion
      </button>
    </div>
    <nav>
      <router-link to="/">Gacha</router-link> |
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
    <router-view :key="$route.path" />
  </div>
  <ConnexionView v-else @utilisateur-connecte="connecterUtilisateur" />
</template>

<style lang="less">
#app {
  .menu {
    .bandeau {
      width: 100%;
      .titre {
        float: left;
      }
      .deconnexion {
        float: right;
      }
    }
    nav {
      text-align: center;
      padding: 30px;

      a {
        font-weight: bold;
        color: #2c3e50;

        &.router-link-exact-active {
          color: #42b983;
        }
      }
    }
  }
}
</style>
