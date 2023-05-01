<template>
  <div class="menu" v-if="utilisateurId">
    <div>
      <span>Bienvenue !</span>
      <button type="button" @click="deconnecterUtilisateur">Déconnexion</button>
    </div>
    <nav>
      <router-link to="/gacha">Gacha</router-link> |
      <router-link to="/collection">Ma collection</router-link> |
      <router-link to="/profil">Profil</router-link>
    </nav>
  </div>
  <ConnexionView v-else @utilisateur-connecte="connecterUtilisateur" />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import ConnexionView from "./views/ConnexionView.vue";

export default defineComponent({
  name: "MainView",
  components: {
    ConnexionView,
  },
  data() {
    let utilisateurId: number = undefined!;
    return { utilisateurId };
  },

  methods: {
    connecterUtilisateur(utilisateurId: number) {
      this.utilisateurId = utilisateurId;
    },

    deconnecterUtilisateur() {
      this.utilisateurId = undefined!;
    },
  },
});
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
