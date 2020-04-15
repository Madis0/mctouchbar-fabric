package io.github.Ashley1227.mctouchbar.widget.config;

import java.util.ArrayList;

public class WidgetConfigOutline {

    public ArrayList<WidgetConfigEntry> entries = new ArrayList<>();

    public WidgetConfigOutline() {

    }

    /**
     * Add a WidgetConfigEntry to the outline.
     * @return the WidgetConfigOutline, so you can chain methods!
     */
    public WidgetConfigOutline addEntry(WidgetConfigEntry entry) {
        this.entries.add(entry);
        return this;
    }
}
