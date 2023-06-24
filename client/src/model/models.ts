export interface ObjetCollection {
    id: number,
    code: string,
    rarete: string,
    categorie: string,
    image: { id: number, description: string, image: string },
}

export interface Utilisateur {
    id: number,
    identifiant: string,
    monnaie: number,
}

export interface UtilisateurCollection {
    objetCollectionId: number,
    quantite: number,
}

export enum NotificationEnum {
    OK = "OK",
    WARNING = "WARNING",
    ERREUR = "ERREUR"
}