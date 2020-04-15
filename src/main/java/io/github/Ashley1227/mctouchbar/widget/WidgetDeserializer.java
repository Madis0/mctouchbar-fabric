package io.github.Ashley1227.mctouchbar.widget;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class WidgetDeserializer extends StdDeserializer<Widget> {

    public WidgetDeserializer() {
        this(null);
    }

    public WidgetDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Widget deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode identifier = node.get("identifier");

        if (identifier == null) {
            MCTouchbar.LOGGER.error("This is unepic. Either this Widget is null in the JSON file, or \"identifier\" inside of it is null. It'll just be replaced by mctouchbar:test so the game doesn't crash.");
            return Widgets.DEFAULT;
        } else {

//            Identifier id = mapper.convertValue(identifier, Identifier.class);
            String namespace = identifier.get("namespace").asText();
            String path = identifier.get("path").asText();

            Identifier id = new Identifier(namespace,path);


            Widget w = MCTouchbarRegistry.WIDGET.get(id);

            return w;
        }
}
}
