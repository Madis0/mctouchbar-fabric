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

	/**
	 * Sets the default value of the WidgetConfigEntry
	 * @return the WidgetConfigEntry object so you can chain it C:
	 */
	public WidgetConfigEntry setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	/**
	 * Set to the properties
	 * @return the WidgetConfigEntry object so you can chain it C:
	 */
	public WidgetConfigEntry<T> set(String key, Object to) {
		this.properties.put(key, to);
		return this;
	}
	/**
	 * Get from the properties
	 */
	public Object get(String key) {
		return this.properties.get(key);
	}

	/**
	 * Check if this entry has the properties given to it
	 */
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