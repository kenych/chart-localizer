package ken.kata.popular.domain;

import ken.kata.popular.domain.exceptions.LanguageNotSupportedException;
import ken.kata.utils.Exceptions;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LanguageTest {

    @Test
    public void testOk() {
        assertThat(Language.fromString("en")).isEqualTo(Language.EN);
    }

    @Test
    public void testNonSupportedLanguage() throws Exception {
        Exceptions.assertThat(new Runnable() {
            @Override
            public void run() {
                Language.fromString("non_supported_lang");
            }
        })
                .throwsException(LanguageNotSupportedException.class);
    }

}
