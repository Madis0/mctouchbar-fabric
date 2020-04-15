package io.github.Ashley1227.mctouchbar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.Widgets;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = MCTouchbarConfigDeserializer.class)
public class MCTouchbarConfig {
    public List<Widget> widgets;

    public List<WidgetConfig> config;

    private ObjectMapper mapper = new ObjectMapper();

    public MCTouchbarConfig() {
        this.widgets = new ArrayList<>();
        this.config = new ArrayList<>();
    }
    public void generateUntil(int number) {
        while (this.widgets.size() < number) {
            this.widgets.add(Widgets.DEFAULT);

        }
        while (this.config.size() < number) {
            this.config.add(WidgetConfig.fromOutline(Widgets.DEFAULT.outline));
        }
    }
    public MCTouchbarConfig setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
        return this;
    }
    public MCTouchbarConfig setConfig(List<WidgetConfig> config) {
        this.config = config;
        return this;
    }
    public MCTouchbarConfig writeToJSON(File f) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(f,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public MCTouchbarConfig readFromJSON(File f) {
        try {
            MCTouchbarConfig cfg = mapper.readValue(f,MCTouchbarConfig.class);
            return cfg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
