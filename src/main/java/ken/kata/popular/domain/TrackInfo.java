package ken.kata.popular.domain;


import com.google.common.base.Optional;

public class TrackInfo {
    private long trackId;
    private Heading heading;

    //not json Unmarshalling
    public TrackInfo() {
    }

    public TrackInfo(long trackId, Heading heading) {
        this.trackId = trackId;
        this.heading = heading;
    }

    public long getTrackId() {
        return trackId;
    }

    public Heading getHeading() {
        return heading;
    }

    public Optional<Track> getTrackBy(Language language) {
        return heading.getTrackBy(language);
    }
}
