package io.github.Ashley1227.mctouchbar.widget.widgets;

import com.thizzer.jtouchbar.JTouchBar;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

public class DebugWidget extends Widget {

    @Override
    public void addToTouchbar(JTouchBar jTouchBar, int index,  WidgetConfig config) {
        super.addToTouchbar(jTouchBar, index, config);

        this.addButtonToTouchbar(new TranslatableText("widget.mctouchbar.debug"), e -> {
            MinecraftClient.getInstance().options.debugEnabled = !MinecraftClient.getInstance().options.debugEnabled;
        });
    }
}
