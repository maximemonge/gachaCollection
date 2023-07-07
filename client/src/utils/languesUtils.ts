import { useI18n } from "vue-i18n";

export const LANGAGE_CACHE = "LANGAGE_CACHE";

export function getLangues() {
    return [
        {id: 'fr', libelle: 'Français', emoji: '🇫🇷'},
        {id: 'en', libelle: 'English', emoji: '🇺🇸'},
        {id: 'jp', libelle: '日本語', emoji: '🇯🇵'},
    ]
}

export function setLangageDansCache(langage: string) {
    sessionStorage.setItem(LANGAGE_CACHE, langage);
}