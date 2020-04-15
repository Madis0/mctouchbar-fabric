package io.github.Ashley1227.mctouchbar.widget.config;

import io.github.Ashley1227.mctouchbar.MCTouchbar;

import java.util.HashMap;


public class WidgetConfig {
    public HashMap properties = new HashMap<String,Object>();
//    protected Widget widget;

    public WidgetConfig() {
    }

    /**
     * get what the user has set "key" to be
     */
    public Object get(String key) {
        return this.properties.get(key);
    }
    /**
     * called when the user saves the config. Sets "key" in the config to "val"
     */
    public Object set(String key, Object val) {
        return this.properties.put(key,val);
    }

    /**
     * generate a WidgetConfig object from the WidgetConfigOutline provided. Sets all the keys in that to their default values.
     * @return the generated WidgetConfig object
     */
    public static WidgetConfig fromOutline(WidgetConfigOutline outline) {
        WidgetConfig config = new WidgetConfig();
        for(WidgetConfigEntry w : outline.entries) {
            config.properties.put(w.translationKey, w.defaultValue);
            MCTouchbar.LOGGER.info(w.translationKey);
        }
        return config;
    }
}
