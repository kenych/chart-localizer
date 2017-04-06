package ken.kata.popular.service;

import ken.kata.popular.domain.Language;
import ken.kata.popular.domain.LocalizedChart;
import ken.kata.popular.domain.NonLocalizedChart;

public interface Localizer {
    LocalizedChart localize(NonLocalizedChart nonLocalizedChart, Language language);
}
