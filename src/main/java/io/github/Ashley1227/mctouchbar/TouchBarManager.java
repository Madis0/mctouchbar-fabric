package io.github.ashley1227.mctouchbar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thizzer.jtouchbar.JTouchBar;
import io.github.ashley1227.mctouchbar.config.MCTouchbarConfig;
import io.github.ashley1227.mctouchbar.util.FramebufferUtils;
import io.github.ashley1227.mctouchbar.widget.Widget;
import io.github.ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFWNativeCocoa;

import java.io.File;

public class TouchBarManager {
	public static MCTouchbarConfig config = new MCTouchbarConfig();
	public static final File CFG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "mctouchbar.json");

	public static long handle;
	public static JTouchBar jTouchBar;

	public static void init() {
		loadConfig();
//		ClientTickCallback.EVENT.register(TouchBarManager::tick);
		HudRenderCallback.EVENT.register(TouchBarManager::hudRender);
	}
	public static void tick(MinecraftClient client) {
		for (int i = 0; i < config.widgets.size(); i++) {
			Widget w = config.widgets.get(i);
			WidgetConfig c = config.config.get(i);

			w.tick(c, i);
		}
	}
	public static void hudRender(float v) {
		for (int i = 0; i < config.widgets.size(); i++) {
			Widget w = config.widgets.get(i);
			WidgetConfig c = config.config.get(i);

			w.tick(c, i);
		}
		PlayerEntity player = MinecraftClient.getInstance().player;
		if(player != null) {
			for (int i = 0; i < 10; i++) {
				ItemStack invStack = player.inventory.getInvStack(i);
				FramebufferUtils.renderItemThing(MinecraftClient.getInstance().getFramebuffer(),invStack,64);
//				if (!player.inventory.getInvStack(i).equals(hotbarItems[i])) {
//					FramebufferUtils.renderItemThing(MinecraftClient.getInstance().getFramebuffer(), );
//					hotbarImages.set(i, FramebufferUtils.getItemStackImg(invStack, 64));
//					hotbarItems[i] = invStack;
//
//					updated = true;
//				}
			}
		}

	}
	public static void reloadTouchbar() {
		if (!RenderSystem.isOnRenderThread()) {
			RenderSystem.recordRenderCall(TouchBarManager::addWidgetsToTouchbar);
		} else {
			addWidgetsToTouchbar();
		}
	}
	private static void addWidgetsToTouchbar() {
		jTouchBar = new JTouchBar();

//        addScrubber();

		for (int i = 0; i < config.widgets.size(); i++) {
			Widget w = config.widgets.get(i);
			WidgetConfig c = config.config.get(i);

			w.addToTouchbar(jTouchBar, i, c);
		}
		jTouchBar.show(GLFWNativeCocoa.glfwGetCocoaWindow(handle));
	}
	public static void loadConfig() {
		config = config.readFromJSON(CFG_FILE);
		if (config == null) {
			config = new MCTouchbarConfig();
		}
		config.generateUntil(10);
	}

	public static void saveConfig() {
		config.writeToJSON(CFG_FILE);
	}
}
