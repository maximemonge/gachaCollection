<script lang="ts">
import { defineComponent, ref } from "vue";
import axios from "axios";
import NotificationComponent from "@/components/NotificationComponent.vue";
import { NotificationEnum } from "@/model/models";

export default defineComponent({
  name: "ConnexionView",
  emits: ["utilisateurConnecte"],
  setup() {
    const notificationConnexion = ref(NotificationComponent);
    return { notificationConnexion };
  },
  data() {
    let identifiant: string = undefined!;
    let motDePasse: string = undefined!;
    let creerCompte: boolean = false;
    return { identifiant, motDePasse, creerCompte };
  },
  methods: {
    async connecterUtilisateur() {
      if (this.validerChampsFormulaire()) {
        await axios
          .post("http://localhost:3000/user/login", {
            identifiant: this.identifiant,
            motDePasse: this.motDePasse,
          })
          .then((reponse) => {
            this.$emit("utilisateurConnecte", reponse.data);
          })
          .catch(() => {
            this.notificationConnexion.afficherNotificationErreur(
              "Identifiant ou mot de passe incorrect"
            );
          });
      } else {
        this.notificationConnexion.afficherNotificationWarning(
          "Veuillez remplir tous les champs"
        );
      }
    },
    async creerUtilisateur() {
      if (this.validerChampsFormulaire()) {
        await axios
          .post("http://localhost:3000/user/new", {
            identifiant: this.identifiant,
            motDePasse: this.motDePasse,
          })
          .then((reponse) => {
            this.$emit("utilisateurConnecte", reponse.data);
          })
          .catch(() => {
            this.notificationConnexion.afficherNotificationErreur(
              "Échec de la création de l'utilisateur"
            );
          });
      } else {
        this.notificationConnexion.afficherNotificationWarning(
          "Veuillez remplir tous les champs"
        );
      }
    },
    validerChampsFormulaire() {
      return this.identifiant?.length && this.motDePasse?.length;
    },
  },
  components: { NotificationComponent },
});
</script>

<template>
  <main class="connexion">
    <form>
      <div class="inputs">
        <input type="text" v-model="identifiant" placeholder="Identifiant" />
        <input
          type="password"
          v-model="motDePasse"
          placeholder="Mot de passe"
        />
      </div>
      <div v-if="creerCompte" class="boutons">
        <button class="validation" type="button" @click="creerUtilisateur">
          S'inscrire
        </button>
        <button type="button" @click="creerCompte = false">Retour</button>
      </div>
      <div v-else class="boutons">
        <button class="validation" type="button" @click="connecterUtilisateur">
          Connexion
        </button>
        <button type="button" @click="creerCompte = true">
          Je n'ai pas de compte
        </button>
      </div>
    </form>
  </main>
  <NotificationComponent ref="notificationConnexion"></NotificationComponent>
</template>

<style lang="less">
.connexion {
  margin-left: 30%;
  margin-right: 30%;
  border: 1px black solid;
  text-align: center;
  .inputs {
    padding: 10px;
    input {
      margin-left: 5px;
    }
  }
  .boutons {
    padding: 10px;

    .validation {
      background-color: rgb(129, 250, 129);
    }

    button {
      margin-left: 5px;
    }
  }
}
</style>
