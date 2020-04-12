package io.github.Ashley1227.mctouchbar.widget.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.widget.Widget;

import java.util.HashMap;


public class WidgetConfig {
    public HashMap properties = new HashMap<String,Object>();
//    protected Widget widget;

    public WidgetConfig() {
    }
    public Object get(String key) {
        return this.properties.get(key);
    }
    public Object set(String key, Object val) {
        return this.properties.put(key,val);
    }

    public static WidgetConfig fromOutline(WidgetConfigOutline outline) {
        WidgetConfig config = new WidgetConfig();
        for(WidgetConfigEntry w : outline.entries) {
            config.properties.put(w.translationKey, w.defaultValue);
            MCTouchbar.LOGGER.info(w.translationKey);
        }
        return config;
    }
}
