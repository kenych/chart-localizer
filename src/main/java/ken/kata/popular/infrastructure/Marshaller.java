package ken.kata.popular.infrastructure;

import ken.kata.popular.domain.LocalizedChart;
import ken.kata.popular.domain.NonLocalizedChart;

public interface Marshaller {
     NonLocalizedChart unmarshal(String fileName);
     void marshal(LocalizedChart localizedChart, String fileName);
}

