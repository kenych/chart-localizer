package ken.kata.popular.infrastructure;

import ken.kata.popular.domain.Heading;
import ken.kata.popular.domain.Language;
import ken.kata.popular.domain.Track;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CustomHeadingDeserializer extends JsonDeserializer<Heading> {

    public static final String TRACK_TITLE = "trackTitle";
    public static final String ARTIST_NAME = "artistName";

    @Override
    public Heading deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        Heading heading = new Heading(node.size());

        for (Iterator<Map.Entry<String, JsonNode>> iterator = node.getFields(); iterator.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = iterator.next();

            String languageCode = entry.getKey();

            JsonNode trackNode = entry.getValue();
            JsonNode titleNode = trackNode.get(TRACK_TITLE);
            JsonNode artistNode = trackNode.get(ARTIST_NAME);

            heading.addTrack(
                    new Track(titleNode.asText(), artistNode.asText()),
                    Language.fromString(languageCode));
        }

        return heading;
    }


}