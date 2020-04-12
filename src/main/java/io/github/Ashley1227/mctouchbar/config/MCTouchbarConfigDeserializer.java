package io.github.Ashley1227.mctouchbar.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.WidgetDeserializer;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MCTouchbarConfigDeserializer extends StdDeserializer<MCTouchbarConfig> {

    public MCTouchbarConfigDeserializer() {
        this(null);
    }
    public MCTouchbarConfigDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public MCTouchbarConfig deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = jp.getCodec().readTree(jp);
        WidgetDeserializer widgetDeserializer = new WidgetDeserializer();

        ArrayNode widgetsNode = (ArrayNode)node.get("widgets");

        ArrayList<Widget> widgetsList = new ArrayList<>();

        for(int i = 0; i < widgetsNode.size(); i++) {
            JsonNode n = widgetsNode.get(i);
            JsonParser p = mapper.getJsonFactory().createJsonParser(n.toString());
            widgetsList.add(widgetDeserializer.deserialize(p, ctxt));
        }

        JsonNode configNode = node.get("config");

//        ArrayList<WidgetConfig> config = mapper.readValue(string, ArrayList.class);
        WidgetConfig[] config = mapper.readValue(configNode.toString(), WidgetConfig[].class);
        ArrayList list = new ArrayList(Arrays.asList(config));

        return new MCTouchbarConfig().setWidgets(widgetsList).setConfig(list);
    }
}