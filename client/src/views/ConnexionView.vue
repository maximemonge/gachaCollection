<script lang="ts">
import { PropType, defineComponent, ref } from "vue";
import axios from "axios";
import NotificationComponent from "@/components/NotificationComponent.vue";
import { useI18n } from "vue-i18n";
import { ThemeEnum } from "@/utils/themeUtils";

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
    return { identifiant, motDePasse, creerCompte, trad: useI18n().t };
  },
  props: {
    theme: ThemeEnum as unknown as PropType<ThemeEnum>,
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
              this.trad("connexion.erreur.mauvaisids")
            );
          });
      } else {
        this.notificationConnexion.afficherNotificationWarning(
          this.trad("connexion.erreur.champsmanquants")
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
              this.trad("connexion.erreur.creationuser")
            );
          });
      } else {
        this.notificationConnexion.afficherNotificationWarning(
          this.trad("connexion.erreur.champsmanquants")
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
  <main
    class="connexion"
    :class="[theme === 'SOMBRE' ? 'theme-sombre' : 'theme-clair']"
  >
    <form>
      <div class="connexion-inputs">
        <input
          type="text"
          v-model="identifiant"
          :placeholder="trad('connexion.identifiant')"
        />
        <input
          type="password"
          v-model="motDePasse"
          :placeholder="trad('connexion.motdepasse')"
        />
      </div>
      <div v-if="creerCompte" class="connexion-boutons">
        <button
          class="connexion-boutons-validation"
          type="button"
          @click="creerUtilisateur"
        >
          {{ trad("connexion.sinscrire") }}
        </button>
        <button
          type="button"
          @click="creerCompte = false"
          class="connexion-boutons-autre"
        >
          {{ trad("connexion.retour") }}
        </button>
      </div>
      <div v-else class="connexion-boutons">
        <button
          class="connexion-boutons-validation"
          type="button"
          @click="connecterUtilisateur"
        >
          {{ trad("connexion.connexion") }}
        </button>
        <button
          type="button"
          @click="creerCompte = true"
          class="connexion-boutons-autre"
        >
          {{ trad("connexion.creercompte") }}
        </button>
      </div>
    </form>
  </main>
  <NotificationComponent ref="notificationConnexion"></NotificationComponent>
</template>

<style lang="less">
@import "../common/less/theme.less";
.connexion {
  margin-left: 30%;
  margin-right: 30%;
  border: 1px solid;
  text-align: center;
  z-index: 1;

  &-inputs {
    padding: 10px;
    input {
      margin-left: 5px;
    }
  }

  &-boutons {
    padding: 10px;

    &-validation {
      border-width: 1px;
      background-color: #81fa81;
    }

    &-autre {
      border-width: 1px;
      background-color: #e0e3e0;
    }

    button {
      margin-left: 5px;
    }
  }
}
</style>
