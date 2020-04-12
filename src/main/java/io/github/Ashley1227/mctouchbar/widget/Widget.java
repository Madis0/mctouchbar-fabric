package io.github.Ashley1227.mctouchbar.widget;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import net.minecraft.util.Identifier;

import java.io.Serializable;

//@JsonSerialize(using = WidgetSerializer.class)

//@JsonDeserialize(using = WidgetDeserializer.class)
public class Widget implements Serializable {

    @JsonIgnore
    public WidgetConfigOutline outline;

    public Widget() {
        this(new WidgetConfigOutline());
    }
    public Widget(WidgetConfigOutline outline) {
        this.outline = outline;
    }
    public WidgetConfigOutline getOutline() {
        return this.outline;
    }
    public Identifier getIdentifier() {
        return MCTouchbarRegistry.WIDGET.getId(this);
    }
    public String toString() {
        return this.getIdentifier().toString();
    }
}
