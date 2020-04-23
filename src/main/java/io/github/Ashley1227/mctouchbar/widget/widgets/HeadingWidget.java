package io.github.Ashley1227.mctouchbar.widget.widgets;

import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.item.view.TouchBarTextField;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.minecraft.client.MinecraftClient;

public class HeadingWidget extends Widget {
	private TouchBarTextField textField;

	public HeadingWidget() {

	}
	@Override
	public void tick(WidgetConfig config, int index) {
		if(this.textField != null)
			this.textField.setStringValue(getHeadingString());
	}

	@Override
	public void addToTouchbar(JTouchBar jTouchBar, int index, WidgetConfig config) {
		super.addToTouchbar(jTouchBar, index, config);
		this.textField = addStringToTouchbar(getHeadingString(), false);
	}
	public String getHeadingString() {
		if(MinecraftClient.getInstance().player == null) {
			return " ";
		} else {
			return Math.round(MinecraftClient.getInstance().player.yaw % 360) + "° " + MinecraftClient.getInstance().player.getHorizontalFacing();
		}
	}
}
