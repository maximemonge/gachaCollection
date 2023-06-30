import { Utilisateur } from "@/model/models";
import { useRoute } from "vue-router";

const UTILISATEUR_CONNECTE_CACHE = "UTILISATEUR_CONNECTE_CACHE";

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

export function getUtilisateurFromCache(): Utilisateur {
    return JSON.parse(sessionStorage.getItem(UTILISATEUR_CONNECTE_CACHE)!);
}

export function setUtilisateurDansCache(utilisateur: Utilisateur) {
    sessionStorage.setItem(UTILISATEUR_CONNECTE_CACHE, JSON.stringify(utilisateur));
}

export function removeUtilisateurFromCache() {
    sessionStorage.removeItem(UTILISATEUR_CONNECTE_CACHE);
}