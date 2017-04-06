package ken.kata.popular.domain;

import java.util.ArrayList;
import java.util.List;

public class NonLocalizedChart {
    private String title;
    private List<TrackInfo> entries;

    //for json Unmarshalling
    public NonLocalizedChart() {
    }

    public NonLocalizedChart(String title) {
        this.title = title;
        entries = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<TrackInfo> getEntries() {
        return entries;
    }
}
