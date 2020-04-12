package io.github.Ashley1227.mctouchbar;

import io.github.Ashley1227.mctouchbar.config.MCTouchbarConfig;
import io.github.Ashley1227.mctouchbar.widget.Widgets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
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

	public static long handle;
	public static JTouchBar jTouchBar;

	public static final Logger LOGGER = LogManager.getLogger(MCTouchbar.class);

	@Override
	public void onInitializeClient() {
		Widgets.init();

		loadConfig(CFG_FILE);
		LOGGER.debug("MCTouchbar initialized");
	}
	public static void onWindowLoad(long handle) {
		MCTouchbar.handle = handle;

		jTouchBar = new JTouchBar();

		jTouchBar.setCustomizationIdentifier("MCTouchbar");

		TouchBarButton optionsBtn = new TouchBarButton();
		optionsBtn.setTitle("Touchbar Options");

		optionsBtn.setAction(new TouchBarViewAction() {
			@Override
			public void onCall( TouchBarView view ) {
//				MinecraftClient.getInstance().openScreen(new OptionsScreen(new OptionsGui()));
				System.out.println("fortnite");
			}
		});

		jTouchBar.addItem(new TouchBarItem("optionsBtn", optionsBtn, true));

		jTouchBar.show(
				GLFWNativeCocoa.glfwGetCocoaWindow(handle)
		);
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
