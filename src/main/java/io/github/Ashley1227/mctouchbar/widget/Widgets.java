package io.github.Ashley1227.mctouchbar.widget;

import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigEntry;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigEntryType;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import io.github.Ashley1227.mctouchbar.widget.widgets.DebugWidget;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Widgets {

    public static Widget TEST = new Widget(new WidgetConfigOutline()
            .addEntry(new WidgetConfigEntry<Integer>(WidgetConfigEntryType.INTEGER,"test.test.int").setDefaultValue(49293))
            .addEntry(new WidgetConfigEntry<String>(WidgetConfigEntryType.STRING,"test.test.string").setDefaultValue("EPic"))
            .addEntry(new WidgetConfigEntry<Boolean>(WidgetConfigEntryType.BOOLEAN,"test.test.boolean").setDefaultValue(false))
    );
    public static Widget TEST2 = new Widget(new WidgetConfigOutline().addEntry(new WidgetConfigEntry<Integer>(WidgetConfigEntryType.INTEGER,"test.test.woeowosdusnkjdakjsn").setDefaultValue(492238493)));

    public static Widget DEBUG = new DebugWidget(new WidgetConfigOutline());

    public static Widget DEFAULT = TEST;

    public static void init() {
        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"test"), TEST);
        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"test2"), TEST2);

        Registry.register(MCTouchbarRegistry.WIDGET, new Identifier(MCTouchbar.MODID,"debug"), DEBUG);
    }
}
