package ken.kata.popular.domain;

public class Track {
    //immutable value object, no need for getters/setters
    public final String trackTitle;
    public final String artistName;

    public Track(String trackTitle, String artistName) {
        this.trackTitle = trackTitle;
        this.artistName = artistName;
    }
}
