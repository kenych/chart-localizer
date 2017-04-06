package ken.kata.popular.infrastructure;


import ken.kata.popular.domain.Heading;
import ken.kata.popular.domain.LocalizedChart;
import ken.kata.popular.domain.NonLocalizedChart;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonMarshaller implements Marshaller {

    public static final String UNMARSHALLING_ERROR = "Unmarshalling error: \n";
    public static final String MARSHALLING_ERROR = "Marshalling error: \n";
    private ObjectMapper mapper;

    public JsonMarshaller() {
        mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule("CustomHeadingDeserializerModule", new Version(1, 1, 1, null));
        simpleModule.addDeserializer(Heading.class, new CustomHeadingDeserializer());
        mapper.registerModule(simpleModule);
    }

    @Override
    public NonLocalizedChart unmarshal(String fileName) {
        try {
            return mapper.readValue(new File(fileName), NonLocalizedChart.class);
        } catch (IOException e) {
            throw new RuntimeException(UNMARSHALLING_ERROR + e.getMessage(), e);
        }
    }

    @Override
    public void marshal(LocalizedChart localizedChart, String fileName) {
        try {
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(mapper.writeValueAsString(localizedChart));
            }
        } catch (IOException e) {
            throw new RuntimeException(MARSHALLING_ERROR + e.getMessage(), e);
        }
    }


}
