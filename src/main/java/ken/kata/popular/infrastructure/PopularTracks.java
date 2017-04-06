package ken.kata.popular.infrastructure;

import ken.kata.popular.domain.Request;
import ken.kata.popular.service.InputFileDirectoryBasedPolicy;
import ken.kata.popular.service.LocalizerImpl;
import ken.kata.popular.service.PopularTracksService;
import ken.kata.popular.service.PopularTracksServiceFacade;

import static ken.kata.popular.infrastructure.Config.DEFAULT_LANGUAGE;
import static ken.kata.popular.infrastructure.Config.outputFileName;

/**
 * TrackInfo point to the Popular Tracks app.
 */
public class PopularTracks {


    public static void main(String[] args) {

        PopularTracksService popularTracksService = new PopularTracksServiceFacade(
                new JsonMarshaller(),
                new LocalizerImpl(DEFAULT_LANGUAGE),
                new InputFileDirectoryBasedPolicy(outputFileName));

        try {
            popularTracksService.localise(new Request(args));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}