package io.github.Ashley1227.mctouchbar.widget;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.common.Color;
import com.thizzer.jtouchbar.item.TouchBarItem;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import com.thizzer.jtouchbar.item.view.TouchBarSlider;
import com.thizzer.jtouchbar.item.view.TouchBarTextField;
import com.thizzer.jtouchbar.item.view.TouchBarView;
import com.thizzer.jtouchbar.item.view.action.TouchBarViewAction;
import com.thizzer.jtouchbar.slider.SliderActionListener;
import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFWNativeCocoa;

import java.io.Serializable;

@JsonSerialize(using = WidgetSerializer.class)

@JsonDeserialize(using = WidgetDeserializer.class)
public class Widget implements Serializable {

    @JsonIgnore
    public WidgetConfigOutline outline;
    @JsonIgnore
    protected WidgetConfig config;
    @JsonIgnore
    protected int index;

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

    public void addToTouchbar(JTouchBar jTouchBar, int index, WidgetConfig config) {
        this.jTouchBar = jTouchBar;
        this.config = config;
        this.index = index;
    }

    public void tick(WidgetConfig config, int index) {

    }
    public TouchBarButton addButtonToTouchbar(String title, TouchBarViewAction action) {
        TouchBarButton btn = new TouchBarButton();
        btn.setTitle(title);
        btn.setAction(action);
        btn.setBezelColor(Color.DARK_GRAY);
        this.jTouchBar.addItem(new TouchBarItem(title + this.index, btn));
        return btn;
    }
    public TouchBarButton addButtonToTouchbar(TranslatableText title, TouchBarViewAction action) {
        TouchBarButton btn = new TouchBarButton();
        btn.setTitle(title.asFormattedString());

        btn.setAction(action);

        btn.setBezelColor(Color.DARK_GRAY);

        this.jTouchBar.addItem(new TouchBarItem(title.asString() + this.index, btn));

        return btn;
    }
    public TouchBarSlider addSliderToTouchbar(TranslatableText title, int min, int max, SliderActionListener actionListener) {
        TouchBarSlider slider = new TouchBarSlider();
        slider.setMinValue(min);
        slider.setMaxValue(max);

        slider.setActionListener(actionListener);

        this.jTouchBar.addItem(new TouchBarItem(title.asString() + this.index, slider));

        return slider;
    }
    public TouchBarTextField addTranslatableTextToTouchbar(TranslatableText text) {
        String title = text.asFormattedString();

        TouchBarTextField textField = new TouchBarTextField();
        textField.setStringValue(title == null ? "" : title);

        this.jTouchBar.addItem(new TouchBarItem(title + this.index, textField));

        return textField;
    }
    @Deprecated
    public TouchBarTextField addStringToTouchbar(String str) {
        String title = str;

        TouchBarTextField textField = new TouchBarTextField();
        textField.setStringValue(title == null ? "" : title);

        this.jTouchBar.addItem(new TouchBarItem(title + this.index, textField));

        return textField;
    }
    @Deprecated
    public TouchBarTextField addTextToTouchbar(Text text) {
        String title = text.asFormattedString();

        TouchBarTextField textField = new TouchBarTextField();
        textField.setStringValue(title == null ? "" : title);

        this.jTouchBar.addItem(new TouchBarItem(title + this.index, textField));

        return textField;
    }
}
