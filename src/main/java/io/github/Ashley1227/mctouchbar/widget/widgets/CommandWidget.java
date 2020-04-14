package io.github.Ashley1227.mctouchbar.widget.widgets;

import com.thizzer.jtouchbar.JTouchBar;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigEntry;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigEntryType;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class CommandWidget extends Widget {

    public CommandWidget() {
        super(new WidgetConfigOutline()
                .addEntry(new WidgetConfigEntry(WidgetConfigEntryType.STRING,"widget.mctouchbar.command.command").setDefaultValue("say hi"))
                .addEntry(new WidgetConfigEntry(WidgetConfigEntryType.STRING,"widget.mctouchbar.command.title").setDefaultValue("Execute"))
        );
    }

    @Override
    public void addToTouchbar(JTouchBar jTouchBar, int index, WidgetConfig config) {
        super.addToTouchbar(jTouchBar, index, config);

        addButtonToTouchbar((String)config.get("widget.mctouchbar.command.title"), e -> {
            MinecraftClient.getInstance().player.sendChatMessage((String)config.get("widget.mctouchbar.command.command"));
        });

    }
}
