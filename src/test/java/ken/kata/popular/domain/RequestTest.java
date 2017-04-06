package ken.kata.popular.domain;

import org.fest.assertions.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ken.kata.utils.Exceptions.assertThat;

public class RequestTest {

    public static final String PATH = "dummy.json";
    public static final String NON_EXISTING_FILE = "non-existing.json";
    public static final String LANGUAGE = Language.EN.name();

    File file;
    public static final List<Language> LANGUAGES = Arrays.asList(Language.EN, Language.IT);

    @Before
    public void setup() throws IOException {
        file = new File(PATH);
        file.createNewFile();
    }

    @Test
    public void testRequestWithNullArguments() throws Exception {
        assertThat(new Runnable() {
            @Override
            public void run() {
                new Request(null);
            }
        })
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining(Request.ARGUMENT_SIZE_ERROR);
    }

    @Test
    public void testRequestWithWrongArgSizeArgument() throws Exception {
        assertThat(new Runnable() {
            @Override
            public void run() {
                new Request(new String[]{""});
            }
        })
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining(Request.ARGUMENT_SIZE_ERROR);
    }

    @Test
    public void testRequestWithLanguageBlank() throws Exception {
        assertThat(new Runnable() {
            @Override
            public void run() {
                new Request(new String[]{PATH, ""});
            }
        })
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining(Request.LANGUAGE_IS_BLANK);
    }

    @Test
    public void testRequestWithFileBlank() throws Exception {
        assertThat(new Runnable() {
            @Override
            public void run() {
                new Request(new String[]{"", LANGUAGE});
            }
        })
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining(Request.FILE_PATH_IS_BLANK);
    }

    @Test
    public void testRequestWhenFileNotFound() throws Exception {
        assertThat(new Runnable() {
            @Override
            public void run() {
                new Request(new String[]{NON_EXISTING_FILE, LANGUAGE});
            }
        })
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining(Request.FILE_NOT_FOUND);
    }

    @Test
    public void testOk() {
        String[] givenArguments = new String[]{PATH, Language.EN.name(), Language.IT.name()};

        Request request = new Request(givenArguments);

        Assertions.assertThat(request.inputFilePath).isEqualTo(fullPathFrom(PATH));

        Assertions.assertThat(request.getLanguage()).hasSize(2);
        Assertions.assertThat(request.getLanguage()).containsAll(Arrays.asList(Language.EN, Language.IT));
    }

    @After
    public void cleanUp() {
        file.delete();
    }

    private String fullPathFrom(String path) {
        return new File(path).getAbsolutePath();
    }


}
