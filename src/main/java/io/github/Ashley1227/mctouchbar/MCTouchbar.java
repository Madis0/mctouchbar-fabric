package io.github.ashley1227.mctouchbar;

import io.github.ashley1227.mctouchbar.widget.Widgets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MCTouchbar extends DrawableHelper implements ClientModInitializer {

	public static final String MODID = "mctouchbar";
	public static final Logger LOGGER = LogManager.getLogger(MCTouchbar.class);

	@Deprecated
	public static int i = 0;

	@Override
	public void onInitializeClient() {
		if (MinecraftClient.getInstance().IS_SYSTEM_MAC) {
			Widgets.init();

			TouchBarManager.init();
			LOGGER.debug("MCTouchbar initialized");
		} else {
			LOGGER.info("Client is not running a Mac, skipping MCTouchbar initialization.");
		}


	}

	public static void onWindowLoad(long handleOwO) {
		TouchBarManager.handle = handleOwO;
	}
}
