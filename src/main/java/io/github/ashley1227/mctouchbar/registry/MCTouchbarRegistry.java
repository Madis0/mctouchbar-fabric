package io.github.Ashley1227.mctouchbar.registry;


import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MCTouchbarRegistry {

	protected static final Logger LOGGER = LogManager.getLogger();
	public static final MutableRegistry<MutableRegistry<?>> REGISTRIES = new SimpleRegistry();

	public static final Registry<Widget> WIDGET = create(new Identifier(MCTouchbar.MODID,"widget"));


	private static <T> Registry<T> create(Identifier identifier) {
		return putDefaultEntry(identifier, new SimpleRegistry());
	}

	private static <T> DefaultedRegistry<T> create(Identifier identifier, String string2) {
		return (DefaultedRegistry)putDefaultEntry(identifier, new DefaultedRegistry(string2));
	}

	private static <T, R extends MutableRegistry<T>> R putDefaultEntry(Identifier identifier, R mutableRegistry) {
		return (R) Registry.REGISTRIES.add(identifier, mutableRegistry);
	}
}
