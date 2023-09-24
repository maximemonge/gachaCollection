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

export interface PiecePuzzle {
    posX: string,
    posY: string,
    ordre: number,
    vide: boolean,
    index: number,
}

export enum NotificationEnum {
    OK = "OK",
    WARNING = "WARNING",
    ERREUR = "ERREUR"
}