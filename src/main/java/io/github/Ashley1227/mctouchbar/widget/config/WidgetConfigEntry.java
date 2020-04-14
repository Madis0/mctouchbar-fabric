package io.github.Ashley1227.mctouchbar.widget.config;

import io.github.Ashley1227.mctouchbar.MCTouchbar;

import java.util.HashMap;

public class WidgetConfigEntry<T> {

    public String translationKey;

    public WidgetConfigEntryType type;
    public T defaultValue;

    protected HashMap<String,Object> properties = new HashMap<>();

    public WidgetConfigEntry(WidgetConfigEntryType type, String translationKey) {
        this.type = type;
        this.translationKey = translationKey;
    }
    public WidgetConfigEntry setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
    public WidgetConfigEntry<T> set(String key, Object to) {
        this.properties.put(key, to);
        return this;
    }
    public Object get(String key) {
        return this.properties.get(key);
    }

    public boolean hasProperties(String... p) {
        int num = 0;

        for(int i = 0; i < p.length; i++) {
            for (String s1 : properties.keySet()) {
                String s2 = p[i];
                if(s1.equals(s2)) {
                    num++;
                    continue;
                }
            }
        }
        return num == p.length;
    }
    public String toString() {
        return this.translationKey + "(" + this.type +")";
    }
}