package ken.kata.popular.service;

import ken.kata.utils.Exceptions;
import ken.kata.popular.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LocalizerImplTest {

    public static final String IT_TITLE = "IT_TITLE";
    public static final String IT_ARTIST_NAME = "IT_ARTIST_NAME";
    public static final String DEF_TITLE = "EN_TITLE";
    public static final String DEF_ARTIST_NAME = "EN_ARTIST_NAME";
    public static final Language DEFAULT_LANGUAGE = Language.EN;
    Localizer localizer;

    @Before
    public void setUp() throws Exception {
        localizer = new LocalizerImpl(DEFAULT_LANGUAGE);
    }

    @Test
    public void testLocalizeLanguageFound() throws Exception {
        NonLocalizedChart nonLocalizedChart = createNonLocalizedChart(DEFAULT_LANGUAGE);

        LocalizedChart localizedChart = localizer.localize(nonLocalizedChart, Language.IT);

        assertThat(localizedChart.getTitle())
                .isEqualTo(nonLocalizedChart.getTitle());

        assertThat(getTrack(localizedChart).artistName)
                .isEqualTo(getHeading(nonLocalizedChart).getTrackBy(Language.IT).get().artistName);

        assertThat(getTrack(localizedChart).trackTitle)
                .isEqualTo(getHeading(nonLocalizedChart).getTrackBy(Language.IT).get().trackTitle);
    }

    @Test
    public void testLocalizeDefaultLanguageFound() throws Exception {
        NonLocalizedChart nonLocalizedChart = createNonLocalizedChart(DEFAULT_LANGUAGE);

        LocalizedChart localizedChart = localizer.localize(nonLocalizedChart, Language.KO);

        assertThat(localizedChart.getTitle())
                .isEqualTo(nonLocalizedChart.getTitle());

        assertThat(getTrack(localizedChart).artistName)
                .isEqualTo(getHeading(nonLocalizedChart).getTrackBy(DEFAULT_LANGUAGE).get().artistName);

        assertThat(getTrack(localizedChart).trackTitle)
                .isEqualTo(getHeading(nonLocalizedChart).getTrackBy(DEFAULT_LANGUAGE).get().trackTitle);
    }

    @Test
    public void testLocalizeLanguageNotSupported() throws Exception {
        final NonLocalizedChart nonLocalizedChart = createNonLocalizedChart(Language.JA);

        Exceptions.assertThat(new Runnable() {
            @Override
            public void run() {
                localizer.localize(nonLocalizedChart, Language.KO);
            }
        })
                .throwsException(IllegalStateException.class);

    }

    private Heading getHeading(NonLocalizedChart nonLocalizedChart) {
        return nonLocalizedChart.getEntries().get(0).getHeading();
    }

    private Track getTrack(LocalizedChart localizedChart) {
        return localizedChart.getEntries().get(0);
    }

    private NonLocalizedChart createNonLocalizedChart(Language defaultLanguage) {
        NonLocalizedChart nonLocalizedChart = new NonLocalizedChart("xxx");
        Heading heading = new Heading(1);
        heading.addTrack(new Track(IT_TITLE, IT_ARTIST_NAME), Language.IT);
        heading.addTrack(new Track(DEF_TITLE, DEF_ARTIST_NAME), defaultLanguage);
        nonLocalizedChart.getEntries().add(new TrackInfo(1L, heading));

        return nonLocalizedChart;
    }
}
