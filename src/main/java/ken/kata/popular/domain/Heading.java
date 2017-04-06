package ken.kata.popular.domain;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class Heading {

    private Map<Language, Track> tracksByLanguage;

    public Heading(int size) {
        this.tracksByLanguage = new HashMap<>(size);
    }

    public Optional<Track> getTrackBy(Language language) {
        Track track = tracksByLanguage.get(language);
        return track != null ? Optional.of(track) : Optional.<Track>absent();
    }

    public void addTrack(Track track, Language language) {
        tracksByLanguage.put(language, track);
    }
}
