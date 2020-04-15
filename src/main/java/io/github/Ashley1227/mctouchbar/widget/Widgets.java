package io.github.Ashley1227.mctouchbar.widget;

import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import io.github.Ashley1227.mctouchbar.widget.widgets.CommandWidget;
import io.github.Ashley1227.mctouchbar.widget.widgets.DebugWidget;
import io.github.Ashley1227.mctouchbar.widget.widgets.HeadingWidget;
import io.github.Ashley1227.mctouchbar.widget.widgets.TestWidget;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Widgets {
    public static Widget NONE = new Widget(new WidgetConfigOutline());

    public static Widget TEST = new TestWidget();

    public static Widget DEBUG = new DebugWidget();
    public static Widget HEADING = new HeadingWidget();

    public static Widget COMMAND = new CommandWidget();

    public static Widget DEFAULT = NONE;

    public static void init() {
        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"none"), NONE);
        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"test"), TEST);

        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"debug"), DEBUG);
        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"heading"), HEADING);

        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"command"), COMMAND);
    }
}
