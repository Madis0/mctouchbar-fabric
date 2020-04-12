package io.github.Ashley1227.mctouchbar.widget;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class WidgetSerializer extends StdSerializer<Widget> {

    public WidgetSerializer() {
        this(null);
    }

    public WidgetSerializer(Class<Widget> t) {
        super(t);
    }

    @Override
    public void serialize(Widget widget, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        jgen.writeStartObject();
        jgen.writeObjectField("identifier",widget.getIdentifier());
//        jgen.writeObjectField("config",widget.config);
        jgen.writeEndObject();
    }
}