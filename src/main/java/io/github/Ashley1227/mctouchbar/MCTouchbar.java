package io.github.Ashley1227.mctouchbar;

import io.github.Ashley1227.mctouchbar.config.MCTouchbarConfig;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.Widgets;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.util.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.lwjgl.glfw.GLFWNativeCocoa;

import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.item.TouchBarItem;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import com.thizzer.jtouchbar.item.view.TouchBarView;
import com.thizzer.jtouchbar.item.view.action.TouchBarViewAction;

import java.io.File;

public class MCTouchbar implements ClientModInitializer {
	public static final String MODID = "mctouchbar";
	public static final File CFG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "mctouchbar.json");
	public static MCTouchbarConfig config = new MCTouchbarConfig();

	private static long handle;
	public static JTouchBar jTouchBar;

	public static final Logger LOGGER = LogManager.getLogger(MCTouchbar.class);

	@Override
	public void onInitializeClient() {
		Widgets.init();

		loadConfig(CFG_FILE);
		LOGGER.debug("MCTouchbar initialized");
	}
	public static void onWindowLoad(long handleOwO) {
		handle = handleOwO;
	}
	public static void regenTouchbar() {
		jTouchBar = new JTouchBar();
		for(int i = 0; i < config.widgets.size(); i++) {
			Widget w = config.widgets.get(i);
			WidgetConfig c = config.config.get(i);

			w.addToTouchbar(jTouchBar);
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
