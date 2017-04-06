package ken.kata.popular.service;

import com.google.common.base.Preconditions;

import java.io.File;

import static java.io.File.separator;

public class InputFileDirectoryBasedPolicy implements OutputFilePathPolicy {

    private String outputFileName;

    public InputFileDirectoryBasedPolicy(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    @Override
    public String getOutputFilePath(String inputFilePath) {
        String parent = new File(inputFilePath).getParent();
        Preconditions.checkArgument(parent != null, "Can't find parent directory of inputFilePath: " + inputFilePath);
        if (!parent.endsWith(separator)) {
            parent += separator;
        }
        return parent + outputFileName;
    }
}
