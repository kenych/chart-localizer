package ken.kata.popular.service;

import com.google.common.base.Optional;
import ken.kata.popular.domain.*;

public class LocalizerImpl implements Localizer {
    private Language defaultLanguage;

    public LocalizerImpl(Language defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    @Override
    public LocalizedChart localize(NonLocalizedChart nonLocalizedChart, Language language) {
        LocalizedChart localizedChart = new LocalizedChart(nonLocalizedChart.getTitle());

        for (TrackInfo trackInfo : nonLocalizedChart.getEntries()) {
            localizedChart.addTrack(getTrackOrDefaultOrNull(language, trackInfo));
        }

        return localizedChart;
    }

    private Track getTrackOrDefaultOrNull(Language language, TrackInfo trackInfo) {
        Optional<Track> trackByLanguageOrDefault = trackInfo.getTrackBy(language);

        if (trackByLanguageOrDefault.isPresent()) {
            return trackByLanguageOrDefault.get();

        } else {
            trackByLanguageOrDefault = trackInfo.getTrackBy(defaultLanguage);

            if (trackByLanguageOrDefault.isPresent()) {
                return trackByLanguageOrDefault.get();

            } else {
                throw new IllegalStateException("Default language " +
                        defaultLanguage + " is missing for track id: " + trackInfo.getTrackId());
            }
        }

    }
}
