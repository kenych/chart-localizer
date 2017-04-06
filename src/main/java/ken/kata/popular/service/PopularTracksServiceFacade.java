package ken.kata.popular.service;

import ken.kata.popular.domain.LocalizedChart;
import ken.kata.popular.domain.NonLocalizedChart;
import ken.kata.popular.domain.Request;
import ken.kata.popular.infrastructure.Marshaller;


public class PopularTracksServiceFacade implements PopularTracksService {

    private Marshaller marshaller;
    private Localizer localizer;
    private OutputFilePathPolicy outputFilePathPolicy;

    public PopularTracksServiceFacade(Marshaller marshaller, Localizer localizer, OutputFilePathPolicy outputFilePathPolicy) {
        this.marshaller = marshaller;
        this.localizer = localizer;
        this.outputFilePathPolicy = outputFilePathPolicy;
    }

    @Override
    public void localise(Request request) {
        NonLocalizedChart nonLocalizedChart = marshaller.unmarshal(request.inputFilePath);

        LocalizedChart localizedChart = localizer.localize(nonLocalizedChart, request.getLanguage().get(0));

        marshaller.marshal(localizedChart, outputFilePathPolicy.getOutputFilePath(request.inputFilePath));
    }


}
