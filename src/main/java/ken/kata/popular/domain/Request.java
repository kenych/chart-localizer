package ken.kata.popular.domain;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Request {
    public static final String LANGUAGE_IS_BLANK = "Language is blank!";
    public static final String FILE_PATH_IS_BLANK = "File path is blank!";
    public static final String ARGUMENT_SIZE_ERROR = "Please supply a filename for the json file and a language tag to localise to";
    public static final String FILE_NOT_FOUND = "File not found: ";

    //immutable value object, no need for getters/setters
    public final String inputFilePath;

    private List<Language> language;

    public List<Language> getLanguage() {
        return language;
    }

    public Request(String[] args) {

        checkArgument(args != null, ARGUMENT_SIZE_ERROR);
        checkArgument(args.length >= 2, ARGUMENT_SIZE_ERROR);

        checkArgument(StringUtils.isNotBlank(args[0]), FILE_PATH_IS_BLANK);
        checkArgument(StringUtils.isNotBlank(args[1]), LANGUAGE_IS_BLANK);

        //to make sure if full file path not provided app can still find it
        String absolutePath = new File(args[0]).getAbsolutePath();

        checkArgument(new File(absolutePath).exists(), FILE_NOT_FOUND + absolutePath);

        inputFilePath = absolutePath;

        language = new ArrayList(args.length-1);
        for (int i = 1; i < args.length; i++) {
            language.add(Language.fromString(args[i]));
        }
    }

}
