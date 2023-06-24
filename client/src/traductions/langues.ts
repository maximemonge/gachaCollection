import { useI18n } from "vue-i18n";

export function getLangues() {
    return [
        {id: 'fr', libelle: 'Français', emoji: '🇫🇷'},
        {id: 'en', libelle: 'English', emoji: '🇺🇸'},
        {id: 'jp', libelle: '日本語', emoji: '🇯🇵'},
    ]
}