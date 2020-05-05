package io.github.Ashley1227.mctouchbar.widget.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.common.Image;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import io.github.Ashley1227.mctouchbar.util.FramebufferUtils;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

public class DebugWidget extends Widget {
	public TouchBarButton button;

	@Override
	public void addToTouchbar(JTouchBar jTouchBar, int index,  WidgetConfig config) {
		super.addToTouchbar(jTouchBar, index, config);

		button = addButtonToTouchbar("widget.mctouchbar.debug", true, e -> {
			MinecraftClient.getInstance().options.debugEnabled = !MinecraftClient.getInstance().options.debugEnabled;
//            if (!RenderSystem.isOnRenderThread()) {
//                RenderSystem.recordRenderCall(()-> FramebufferUtils.instance.saveItemPic("gamer","fortnite", 500));
//            } else {
//                FramebufferUtils.instance.saveItemPic("gamer","fortnite", 500);
//            }
		});
		button.setImage(new Image("/Users/computer/Dropbox/1.15.2 Default Resources/assets/minecraft/textures/item/quartz.png", true));
	}
}
