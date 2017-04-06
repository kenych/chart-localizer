package ken.kata.popular.domain;

import java.util.ArrayList;
import java.util.List;

public class LocalizedChart {

    private String title;
    private List<Track> entries;

    public String getTitle() {
        return title;
    }

    public List<Track> getEntries() {
        return entries;
    }

    public LocalizedChart(String title) {
        this.entries = new ArrayList<>();
        this.title = title;
    }

    public void addTrack(Track track) {
        entries.add(track);
    }

}
