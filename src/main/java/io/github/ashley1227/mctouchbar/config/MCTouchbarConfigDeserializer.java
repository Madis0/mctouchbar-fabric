package io.github.Ashley1227.mctouchbar.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.WidgetDeserializer;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		ObjectReader reader = mapper.readerFor(new TypeReference<List<WidgetConfig>>() {
		});
		List<WidgetConfig> list = reader.readValue(configNode);

		return new MCTouchbarConfig().setWidgets(widgetsList).setConfig(list);
	}
}