package ken.kata.popular.domain.exceptions;

public class LanguageNotSupportedException extends IllegalArgumentException {
    public LanguageNotSupportedException(String s) {
        super(s);
    }
}
