package ken.kata.popular.domain;

import ken.kata.popular.domain.exceptions.LanguageNotSupportedException;

public enum Language {
    EN, IT, KO, JA, SV;

    public static Language fromString(String code) {
        try {
            return Language.valueOf(code.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LanguageNotSupportedException("No language was found for code: " + code);
        }
    }

}
