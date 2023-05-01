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
}