package io.github.Ashley1227.mctouchbar;

import com.thizzer.jtouchbar.JTouchBar;
import io.github.Ashley1227.mctouchbar.config.MCTouchbarConfig;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.Widgets;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWNativeCocoa;

import java.io.File;

public class MCTouchbar implements ClientModInitializer {
	public static final String MODID = "mctouchbar";
	public static final File CFG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "mctouchbar.json");
	public static MCTouchbarConfig config = new MCTouchbarConfig();

	private static long handle;
	public static JTouchBar jTouchBar;

	public static final Logger LOGGER = LogManager.getLogger(MCTouchbar.class);

	public static boolean isMac = System.getProperty("os.name").toLowerCase().startsWith("mac");

	@Override
	public void onInitializeClient() {
		if(isMac) {
			Widgets.init();

			loadConfig(CFG_FILE);

			ClientTickCallback.EVENT.register(client -> {
				for (int i = 0; i < config.widgets.size(); i++) {
					Widget w = config.widgets.get(i);
					WidgetConfig c = config.config.get(i);

					w.tick(c, i);
				}
			});

			LOGGER.debug("MCTouchbar initialized");
		} else {
			LOGGER.info("Client is not running a Mac, skipping MCTouchbar initialization.");
		}
	}
	public static void onWindowLoad(long handleOwO) {
		handle = handleOwO;
	}
	public static void regenTouchbar() {
		jTouchBar = new JTouchBar();

		for(int i = 0; i < config.widgets.size(); i++) {
			Widget w = config.widgets.get(i);
			WidgetConfig c = config.config.get(i);

			w.addToTouchbar(jTouchBar,i,c);
		}
		jTouchBar.show(GLFWNativeCocoa.glfwGetCocoaWindow(handle));
	}

	public static void loadConfig(File file) {
		config = config.readFromJSON(file);
		if(config == null) {
			config = new MCTouchbarConfig();
		}
		config.generateUntil(10);
	}
	public static void saveConfig(File file) {
		config.writeToJSON(file);
	}
	
}
