package io.github.Ashley1227.mctouchbar.widget.config;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import java.util.ArrayList;

public class WidgetConfigOutline {

    public ArrayList<WidgetConfigEntry> entries = new ArrayList<>();

    public WidgetConfigOutline() {

    }
    public WidgetConfigOutline addEntry(WidgetConfigEntry entry) {
        this.entries.add(entry);
        return this;
    }
}
