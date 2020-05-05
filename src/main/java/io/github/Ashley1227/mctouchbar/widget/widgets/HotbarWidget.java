package io.github.ashley1227.mctouchbar.widget.widgets;

import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.common.Image;
import com.thizzer.jtouchbar.common.ImageName;
import com.thizzer.jtouchbar.item.view.TouchBarScrubber;
import com.thizzer.jtouchbar.scrubber.ScrubberActionListener;
import com.thizzer.jtouchbar.scrubber.ScrubberMode;
import com.thizzer.jtouchbar.scrubber.ScrubberSelectionStyle;
import io.github.ashley1227.mctouchbar.MCTouchbar;
import io.github.ashley1227.mctouchbar.util.FramebufferUtils;
import io.github.ashley1227.mctouchbar.widget.Widget;
import io.github.ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class HotbarWidget extends Widget {

	ArrayList<Image> hotbarImages = new ArrayList<>();
	ItemStack[] hotbarItems = new ItemStack[10];

	{
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
		hotbarImages.add(new Image(ImageName.NSImageNameTouchBarColorPickerFill,false));
	}


	TouchBarScrubber scrubber;

	public HotbarWidget() {
		super(new WidgetConfigOutline());
	}

	@Override
	public void tick(WidgetConfig config, int index) {
		super.tick(config, index);
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		if(player != null) {
			boolean updated = false;
			for(int i = 0; i < 10; i++) {
				if (!player.inventory.getInvStack(i).equals(hotbarItems[i])) {
					ItemStack invStack = player.inventory.getInvStack(i);
					hotbarImages.set(i, FramebufferUtils.getItemStackImg(invStack, 64));
					hotbarItems[i] = invStack;

					updated = true;
				}
			}
			if(updated)
				scrubber.setBackgroundColor(scrubber.getBackgroundColor());
			scrubber.setMode(ScrubberMode.FIXED);
			scrubber.setSelectionBackgroundStyle(ScrubberSelectionStyle.ROUNDED_BACKGROUND_STYLE);
//			scrubber.setSelectionOverlayStyle(ScrubberSelectionStyle.OUTLINE_OVERLAY_STYLE);
		}

	}

	@Override
	public void addToTouchbar(JTouchBar jTouchBar, int index, WidgetConfig config) {
		super.addToTouchbar(jTouchBar, index, config);
		scrubber = addScrubberToTouchbar("gamer", false, new ScrubberActionListener() {
					@Override
					public void didSelectItemAtIndex(TouchBarScrubber scrubber, long index) {
						MCTouchbar.LOGGER.info("e");
					}
				}, hotbarImages);
	}
}
