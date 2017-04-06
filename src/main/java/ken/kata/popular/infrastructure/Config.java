package ken.kata.popular.infrastructure;

import ken.kata.popular.domain.Language;

public class Config {
    public static final Language DEFAULT_LANGUAGE = Language.EN;

    //this name comes from requirements, although I my opinion it would be nice to call it chart-xxx.json where xxx is a language
    public static final String outputFileName = "localised-chart.json";

}
