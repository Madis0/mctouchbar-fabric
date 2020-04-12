package io.github.Ashley1227.mctouchbar.widget.widgets;

import com.thizzer.jtouchbar.JTouchBar;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

public class DebugWidget extends Widget {

    public DebugWidget(WidgetConfigOutline outline) {
        super();
    }

    @Override
    public void addToTouchbar(JTouchBar jTouchBar) {
        super.addToTouchbar(jTouchBar);

        addButtonToTouchbar(new TranslatableText("widget.mctouchbar.debug"), e -> {
            MinecraftClient.getInstance().options.debugEnabled = !MinecraftClient.getInstance().options.debugEnabled;
        });
    }
}
