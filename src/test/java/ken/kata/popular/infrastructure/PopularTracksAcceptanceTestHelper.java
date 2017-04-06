package ken.kata.popular.infrastructure;

import ken.kata.popular.service.InputFileDirectoryBasedPolicy;
import ken.kata.utils.FileUtils;

import java.io.*;

public class PopularTracksAcceptanceTestHelper {

    protected final static String INPUT_FILE_NAME = "chart.json";
    protected final static String INPUT_FILE_NOT_EXISTING = "non-exisitng.json";
    protected final static String INPUT_FILE_CORRUPTED = "chart-corrupted.json";
    protected final static String OUTFILE_NAME = "localised-chart.json";
    protected final static String EXPECTED_JSON_SAMPLE_FILE_NAME = "output-chart.json";

    protected ByteArrayOutputStream out;
    protected PrintStream oldOut;

    protected boolean errorIsSentToOutput(String error) {
        return out.toString().contains(error);
    }

    protected void redirectStandartOutput() {
        out = new ByteArrayOutputStream();
        oldOut = System.out;
        System.setOut(new PrintStream(out));
    }

    protected boolean fileIsCreated() {
        return new File(getOutputFilePath()).exists();
    }

    protected String expectedOutputJson() throws IOException {
        return readFile(FileUtils.pathFor(EXPECTED_JSON_SAMPLE_FILE_NAME));
    }

    protected String actualOutputJson() throws IOException {
        return readFile(getOutputFilePath());
    }

    protected String getOutputFilePath() {
        return new InputFileDirectoryBasedPolicy(OUTFILE_NAME).getOutputFilePath(FileUtils.pathFor(INPUT_FILE_NAME));
    }

    protected void whenApplicationExecuted() {
        PopularTracks.main(new String[]{FileUtils.pathFor(INPUT_FILE_NAME), "it"});
    }

    protected String readFile(String filePath) throws IOException {
        String actualJson;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            actualJson = bufferedReader.readLine();
        }
        return actualJson;
    }


    protected void deleteCreatedFile() {
        new File(getOutputFilePath()).delete();
    }

    protected void whenApplicationExecutedWithWrongInput() {
        PopularTracks.main(new String[]{});
    }

    protected void whenApplicationExecutedWithNotExistingFileInput() {
        PopularTracks.main(new String[]{INPUT_FILE_NOT_EXISTING, "it"});
    }

    protected void whenApplicationExecutedWithCorruptedFileInput() {
        PopularTracks.main(new String[]{FileUtils.pathFor(INPUT_FILE_CORRUPTED), "it"});
    }

    protected void restoreOutout() {
//        this would cause NLP for any further calls on System.out, so restore to previous value
//        System.setOut(null);
        System.setOut(oldOut);
    }

}
