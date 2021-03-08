package io.github.Ashley1227.mctouchbar.registry;


import com.mojang.serialization.Lifecycle;
import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MCTouchbarRegistry {
	protected static final Logger LOGGER = LogManager.getLogger();

	public static final Lifecycle LIFECYCLE = Lifecycle.stable();
	protected static final RegistryKey REGISTRY_KEY = RegistryKey.ofRegistry(new Identifier(MCTouchbar.MODID, "registry"));
	public static final MutableRegistry<MutableRegistry<?>> REGISTRIES = new SimpleRegistry(REGISTRY_KEY, LIFECYCLE);

	public static final Registry<Widget> WIDGET = create(new Identifier(MCTouchbar.MODID,"widget"));

	private static <T> Registry<T> create(Identifier identifier) {
		return putDefaultEntry(identifier, new SimpleRegistry(REGISTRY_KEY, LIFECYCLE));
	}

	private static <T> DefaultedRegistry<T> create(Identifier identifier, String string2) {
		return (DefaultedRegistry)putDefaultEntry(identifier, new DefaultedRegistry(string2, REGISTRY_KEY, LIFECYCLE));
	}

	private static <T, R extends MutableRegistry<T>> R putDefaultEntry(Identifier identifier, R mutableRegistry) {
		return null;
		//return (R) Registry.register(Registry.REGISTRIES, identifier, mutableRegistry);
		//return (R) Registry.REGISTRIES.add(identifier, mutableRegistry);
	}
}
