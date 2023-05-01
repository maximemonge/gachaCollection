<template>
  <main class="connexion">
    <form>
      <input type="text" v-model="identifiant" placeholder="Identifiant" />
      <input type="password" v-model="motDePasse" placeholder="Mot de passe" />
      <div v-if="creerCompte">
        <button type="button" @click="creerUtilisateur">Créer le compte</button>
        <button type="button" @click="creerCompte = false">Retour</button>
      </div>
      <div v-else>
        <button type="button" @click="connecterUtilisateur">Connexion</button>
        <button type="button" @click="creerCompte = true">
          Je n'ai pas de compte
        </button>
      </div>
    </form>
  </main>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import axios from "axios";
import { ref } from "vue";

export default defineComponent({
  name: "ConnexionView",
  data() {
    let identifiant: string = undefined!;
    let motDePasse: string = undefined!;
    let creerCompte: boolean = false;
    return { identifiant, motDePasse, creerCompte };
  },
  methods: {
    async connecterUtilisateur() {
      if (this.validerChampsFormulaire()) {
        try {
          const utilisateurId: number = await axios.post(
            "http://localhost:3000/user/login",
            {
              identifiant: this.identifiant,
              motDePasse: this.motDePasse,
            }
          );
          this.$emit("utilisateurConnecte", utilisateurId);
        } catch (error) {
          console.log("Échec de la connexion");
        }
      } else {
        console.log("TODO CREER NOTIFICATION");
      }
    },

    async creerUtilisateur() {
      if (this.validerChampsFormulaire()) {
        try {
          const utilisateurId: number = await axios.post(
            "http://localhost:3000/user/new",
            {
              identifiant: this.identifiant,
              motDePasse: this.motDePasse,
            }
          );
          this.$emit("utilisateurConnecte", utilisateurId);
        } catch (error) {
          console.log("Échec de la création de l'utilisateur");
        }
      } else {
        console.log("TODO CREER NOTIFICATION");
      }
    },

    validerChampsFormulaire() {
      return this.identifiant?.length && this.motDePasse?.length;
    },
  },
});
</script>
