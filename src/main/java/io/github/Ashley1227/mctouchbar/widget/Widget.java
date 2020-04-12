package io.github.Ashley1227.mctouchbar.widget;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.item.TouchBarItem;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import com.thizzer.jtouchbar.item.view.TouchBarView;
import com.thizzer.jtouchbar.item.view.action.TouchBarViewAction;
import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFWNativeCocoa;

import java.io.Serializable;

//@JsonSerialize(using = WidgetSerializer.class)

//@JsonDeserialize(using = WidgetDeserializer.class)
public class Widget implements Serializable {

    @JsonIgnore
    public WidgetConfigOutline outline;

    @JsonIgnore
    public JTouchBar jTouchBar;

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

    public void addToTouchbar(JTouchBar jTouchBar) {
        this.jTouchBar = jTouchBar;

        this.addButtonToTouchbar(new TranslatableText("text.owo.uwu"), e -> {
            MCTouchbar.LOGGER.info("Forntei");
        });
    }
    public TouchBarButton addButtonToTouchbar(TranslatableText title, TouchBarViewAction action) {
        TouchBarButton btn = new TouchBarButton();
        btn.setTitle(title.asFormattedString());

        btn.setAction(action);

        this.jTouchBar.addItem(new TouchBarItem(title.asString(), btn, false));

        return btn;

    }
}
