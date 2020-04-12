package io.github.Ashley1227.mctouchbar.widget.config;

public class WidgetConfigEntry<T> {

    public String translationKey;

    public WidgetConfigEntryType type;
    public T defaultValue;

    public WidgetConfigEntry(WidgetConfigEntryType type, String translationKey) {
        this.type = type;
        this.translationKey = translationKey;
    }
    public WidgetConfigEntry setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
}