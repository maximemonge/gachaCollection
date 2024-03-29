<script lang="ts">
import { defineComponent } from "vue";
import axios from "axios";
import { ObjetCollection, Utilisateur } from "@/model/models";
import ObjetCollectionComponent from "@/components/ObjetCollectionComponent.vue";
import { getUtilisateurFromCache } from "@/utils/utilisateurUtils";
import { useI18n } from "vue-i18n";

export default defineComponent({
  name: "GachaView",
  emits: ["perteMonnaie"],
  data() {
    let objetCollection: ObjetCollection = undefined!;
    const utilisateur: Utilisateur = getUtilisateurFromCache();
    const coutGacha: number = 1;
    return { objetCollection, utilisateur, coutGacha, trad: useI18n().t };
  },

  methods: {
    async obtenirUnObjet() {
      await axios
        .get(
          "http://localhost:3000/objetCollection/obtenir/user/" +
            this.utilisateur.id +
            "/" +
            this.coutGacha
        )
        .then((reponse) => {
          this.objetCollection = reponse.data;
          this.utilisateur.monnaie -= this.coutGacha;
          this.$emit("perteMonnaie", this.coutGacha);
        })
        .catch(() => {
          console.log(
            "Échec lors de la récupération d'un objet de collection aléatoire"
          );
        });
    },
  },
  computed: {
    isMonnaieSuffisante(): boolean {
      return (
        this.utilisateur.monnaie > 0 &&
        this.utilisateur.monnaie - this.coutGacha >= 0
      );
    },
  },
  components: { ObjetCollectionComponent },
});
</script>

<template>
  <main class="gacha">
    <span>{{ trad("gacha.obtenirobjet") }}</span>
    <button
      :key="'obtentionObjet' + coutGacha"
      :disabled="!isMonnaieSuffisante"
      class="gacha-boutonObtention gacha-monnaie"
      type="button"
      @click="obtenirUnObjet"
    >
      -{{ coutGacha }}
      <img class="inverser-couleurs" src="../assets/argent.png" />
    </button>
    <div v-if="objetCollection" class="gacha-obtention">
      <span>{{ trad("gacha.objetobtenu") }}</span>
      <ObjetCollectionComponent
        :obj-collection="objetCollection"
        :mes-objets="[{ objetCollectionId: objetCollection.id, quantite: 1 }]"
        :afficherQuantite="false"
      ></ObjetCollectionComponent>
    </div>
  </main>
</template>

<style lang="less">
.gacha {
  text-align: center;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  &-monnaie {
    img {
      width: 12px;
      height: 12px;
      margin-top: 3px;
    }
  }

  &-boutonObtention {
    margin-top: 5px;
    height: 25px;
    width: 50px;
    font-size: 16px;
  }

  &-obtention {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 35px;
  }
}
</style>
