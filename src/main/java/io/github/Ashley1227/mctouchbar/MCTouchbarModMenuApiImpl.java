package io.github.Ashley1227.mctouchbar;

import io.github.Ashley1227.mctouchbar.config.MCTouchbarConfig;
import io.github.Ashley1227.mctouchbar.registry.MCTouchbarRegistry;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.Widgets;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigEntry;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MCTouchbarModMenuApiImpl implements ModMenuApi {

    protected Screen parentScreen;
    public int lastTabIndex = 0;

    @Override
    public String getModId() {
        return MCTouchbar.MODID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::create;
    }

    public Screen create(Screen screen) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(screen)
                .setTitle("title." + MCTouchbar.MODID + ".config");
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory("category." + MCTouchbar.MODID + ".general");

        general.addEntry(entryBuilder.startTextDescription("Might add stuff here later lol").build());

        this.parentScreen = screen;

        for (int i = 0; i < 10; i++) {
            Widget w = MCTouchbar.config.widgets.get(i);
            WidgetConfig config = MCTouchbar.config.config.get(i);
            ConfigCategory c = builder.getOrCreateCategory("category." + MCTouchbar.MODID + ".widget" + i);
            c.addEntry(genSlot(builder, entryBuilder, i));
            for (WidgetConfigEntry entry : w.getOutline().entries) {

                Object currValue = config.get(entry.translationKey);

                if (currValue == null) {
                    currValue = entry.defaultValue;
                }
                final WidgetConfig widgetConfig = MCTouchbar.config.config.get(i);
                switch (entry.type) {
                    case INTEGER:
                        c.addEntry(entryBuilder.startIntField(entry.translationKey, (int) currValue)
                                .setDefaultValue((int) entry.defaultValue)
                                .setSaveConsumer(thing -> {
                                    widgetConfig.set(entry.translationKey, thing);
                                }).build());
                        break;
                    case INT_SLIDER:
                        if (entry.hasProperties("min", "max")) {
                            c.addEntry(entryBuilder.startIntSlider(entry.translationKey, (int) currValue, (int) entry.get("min"), (int) entry.get("max"))
                                    .setDefaultValue((int) entry.defaultValue)
                                    .setSaveConsumer(thing -> {
                                        widgetConfig.set(entry.translationKey, thing);
                                    }).build());
                        } else {
                            MCTouchbar.LOGGER.warn("[MCTouchbar] The widget " + w + " has config entry " + entry + " which does not have the correct properties. Skipping this entry.");
                        }
                        break;
                    case DOUBLE:
                        c.addEntry(entryBuilder.startDoubleField(entry.translationKey, (double) currValue)
                                .setDefaultValue((double) entry.defaultValue)
                                .setSaveConsumer(thing -> {
                                    widgetConfig.set(entry.translationKey, thing);
                                }).build());
                        break;
                    case BOOLEAN:

                        c.addEntry(entryBuilder.startBooleanToggle(entry.translationKey, (boolean) currValue)
                                .setDefaultValue((boolean) entry.defaultValue)
                                .setSaveConsumer(thing -> {
                                    widgetConfig.set(entry.translationKey, thing);
                                }).build());

                        break;
                    case STRING:
                        c.addEntry(entryBuilder.startStrField(entry.translationKey, (String) currValue)
                                .setDefaultValue((String) entry.defaultValue)
                                .setSaveConsumer(thing -> {
                                    widgetConfig.set(entry.translationKey, thing);
                                }).build());
                        break;
                    default:
                        break;
                }
            }
        }
        builder.setSavingRunnable(() -> {
            MCTouchbar.saveConfig(MCTouchbar.CFG_FILE);
            MinecraftClient.getInstance().openScreen(this.create(this.parentScreen));

            MCTouchbar.regenTouchbar();
        });

        ClothConfigScreen scr = (ClothConfigScreen) builder.build();
        scr.selectedTabIndex = lastTabIndex;
        scr.nextTabIndex = lastTabIndex + 1;

        return scr;
    }

    private DropdownBoxEntry genSlot(ConfigBuilder builder, ConfigEntryBuilder entryBuilder, int number) {
        return entryBuilder.startDropdownMenu("config." + MCTouchbar.MODID + ".widget" + number,
                DropdownMenuBuilder.TopCellElementBuilder.of(MCTouchbar.config.widgets.size() <= number ? Widgets.DEFAULT : MCTouchbar.config.widgets.get(number), widget -> {
                    if (widget == null) {
                        MCTouchbar.LOGGER.error("[MCTouchbar] This is unepic. Tried to load widget" + widget.toString() + " but it doesn't exist. We're just gonna set it to the default one");
                        return Widgets.DEFAULT;
                    } else {
                        return widget.toString();
                    }
                }),
                DropdownMenuBuilder.CellCreatorBuilder.of(128, 4)
        )
                .setDefaultValue(Widgets.DEFAULT)
                .setSelections(MCTouchbarRegistry.WIDGET.stream().collect(Collectors.toSet()))
                .setSaveConsumer(string -> {
                    if (MCTouchbar.config.widgets.get(number) != MCTouchbarRegistry.WIDGET.get(new Identifier((String) string))) {
                        MCTouchbar.config.widgets.set(number, MCTouchbarRegistry.WIDGET.get(new Identifier((String) string)));
                        MCTouchbar.config.config.set(number, WidgetConfig.fromOutline(MCTouchbarRegistry.WIDGET.get(new Identifier((String) string)).outline));
                        this.lastTabIndex = number;
                    }
                })
                .build();
    }
}
