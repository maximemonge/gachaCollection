<script lang="ts">
import { NotificationEnum } from "@/model/models";
import { defineComponent } from "vue";

export default defineComponent({
  name: "NotificationComponent",
  data() {
    let message: string = undefined!;
    let typeNotification: string = undefined!;
    return { message, typeNotification };
  },
  methods: {
    couleurBackground(): string {
      let couleur: string = "white";
      if (this.typeNotification) {
        switch (this.typeNotification) {
          case NotificationEnum.OK:
            couleur = "green";
            break;
          case NotificationEnum.ERREUR:
            couleur = "red";
            break;
          case NotificationEnum.WARNING:
            couleur = "orange";
            break;
          default:
            couleur = "white";
            break;
        }
      }
      return couleur;
    },

    afficherNotification(message: string, typeNotification: NotificationEnum) {
      this.message = message;
      this.typeNotification = typeNotification;
      this.supprimerNotification();
    },

    afficherNotificationOk(message: string) {
      this.afficherNotification(message, NotificationEnum.OK);
    },

    afficherNotificationWarning(message: string) {
      this.afficherNotification(message, NotificationEnum.WARNING);
    },

    afficherNotificationErreur(message: string) {
      this.afficherNotification(message, NotificationEnum.ERREUR);
    },

    supprimerNotification() {
      setTimeout(() => {
        this.message = undefined!;
        this.typeNotification = undefined!;
      }, 3000);
    },
  },
});
</script>

<template>
  <div
    v-if="message"
    class="rectangle"
    :style="{ background: couleurBackground() }"
  >
    <span>{{ message }}</span>
  </div>
</template>

<style lang="less">
.rectangle {
  position: fixed;
  bottom: 0;
  right: 0;
  width: 200px;
  max-height: 80px;
  overflow: auto;
  margin-bottom: 3px;
  margin-right: 3px;
  border: 2px solid black;
  font-weight: bold;
  font-size: small;
  text-align: center;
  vertical-align: middle;
}
</style>
