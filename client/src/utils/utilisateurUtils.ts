import { Utilisateur } from "@/model/models";
import { useRoute } from "vue-router";

export function getUtilisateurFromRoute(): Utilisateur {
    const route = useRoute();
    const id: number = route.query.utilisateurId as unknown as number;
    const identifiant: string = route.query.utilisateurLogin as string;
    const monnaie: number = route.query.utilisateurMonnaie as unknown as number;
    return {
        id,
        identifiant,
        monnaie,
    };
};