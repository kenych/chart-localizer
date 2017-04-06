package ken.kata.popular.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static ken.kata.popular.domain.Request.ARGUMENT_SIZE_ERROR;
import static ken.kata.popular.domain.Request.FILE_NOT_FOUND;
import static org.fest.assertions.api.Assertions.assertThat;

public class PopularTracksAcceptanceTest extends PopularTracksAcceptanceTestHelper {

    @Before
    public void setup() {
        redirectStandartOutput();
    }

    @Test
        public void testApplicationGeneratesCorrectJson() throws Exception {
        whenApplicationExecuted();

        assertThat(fileIsCreated()).isTrue();
        assertThat(actualOutputJson()).isEqualTo(expectedOutputJson());
    }

    @Test
    public void testApplicationOutputForInputError(){
        whenApplicationExecutedWithWrongInput();

        assertThat(fileIsCreated()).isFalse();
        assertThat(errorIsSentToOutput(ARGUMENT_SIZE_ERROR)).isTrue();
    }

    @Test
    public void testApplicationOutputForFileNotFoundError(){
        whenApplicationExecutedWithNotExistingFileInput();

        assertThat(errorIsSentToOutput(FILE_NOT_FOUND)).isTrue();
    }

    @Test
    public void testApplicationOutputForFileCorruptedError(){
        whenApplicationExecutedWithCorruptedFileInput();

        assertThat(errorIsSentToOutput(JsonMarshaller.UNMARSHALLING_ERROR)).isTrue();
    }

    @After
    public void cleanup() {
        deleteCreatedFile();
        restoreOutout();
    }

}
