export const THEME_CACHE = "THEME_CACHE";

export enum ThemeEnum {
    CLAIR = "CLAIR",
    SOMBRE = "SOMBRE",
}

export function setThemeDansCache(theme: string) {
    if (theme) {
        sessionStorage.setItem(THEME_CACHE, theme);
    }
}

export function getThemeDansCache(): ThemeEnum {
    return getThemeEnumFromString(sessionStorage.getItem(THEME_CACHE)!);
}

function getThemeEnumFromString(valeur: string) {
    let themeEnum = undefined;
    if (valeur == ThemeEnum.SOMBRE) {
        themeEnum = ThemeEnum.SOMBRE;
    }
    else {
        themeEnum = ThemeEnum.CLAIR;
    }
    return themeEnum;
}