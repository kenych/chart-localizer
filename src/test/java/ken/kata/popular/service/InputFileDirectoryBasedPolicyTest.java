package ken.kata.popular.service;

import ken.kata.utils.Exceptions;
import ken.kata.utils.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class InputFileDirectoryBasedPolicyTest {

    public static final String INPUT_FILE = "chart.json";
    public static final String INPUT_FILE_ON_TOP_DIRECTORY_WITH_NO_PARENT = "/test.txt";
    public static final String INCORRECT_INPUT_FILE_PATH = UUID.randomUUID().toString();
    public static final String OUT_FILE = "new.json";

    @Test
    public void testGetOutputFilePath() throws Exception {
        assertThat(new InputFileDirectoryBasedPolicy(OUT_FILE).getOutputFilePath(FileUtils.pathFor(INPUT_FILE)))
                .isEqualTo(FileUtils.pathFor(INPUT_FILE).replace(INPUT_FILE, OUT_FILE));
    }

    @Test
    public void testGetOutputFilePathForIncorrectFilePath() throws Exception {
        Exceptions.assertThat(new Runnable() {
            @Override
            public void run() {
                new InputFileDirectoryBasedPolicy(OUT_FILE).getOutputFilePath(INCORRECT_INPUT_FILE_PATH);
            }
        })
                .throwsException(IllegalArgumentException.class);
    }

    @Test
    @Ignore(value = "Works for me on Mac, not sure what would be behaviour on windows etc though, so disable and run locally only")
    public void testGetOutputFilePathFromTopDirectory() throws Exception {
        assertThat(new InputFileDirectoryBasedPolicy(OUT_FILE).getOutputFilePath(INPUT_FILE_ON_TOP_DIRECTORY_WITH_NO_PARENT))
                .isEqualTo(File.separator + OUT_FILE);
    }



}
