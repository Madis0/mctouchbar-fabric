package io.github.Ashley1227.mctouchbar.widget.widgets;

import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.common.Color;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import io.github.Ashley1227.mctouchbar.widget.Widget;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfig;
import io.github.Ashley1227.mctouchbar.widget.config.WidgetConfigOutline;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

import java.util.Random;

public class TestWidget extends Widget {

    private TouchBarButton[] buttons = new TouchBarButton[9];
    private Color[] colors = {Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.PURPLE};
    private int[] i = new int[9];

    public TestWidget() {
        super(new WidgetConfigOutline()

        );

    }

    @Override
    public void tick(WidgetConfig config, int index) {
        super.tick(config,index);
        if(this.buttons != null && this.buttons[index] != null) {
            this.buttons[index].setBezelColor(this.colors[++i[index] % colors.length]);
        }
    }

    @Override
    public void addToTouchbar(JTouchBar jTouchBar, int index, WidgetConfig config) {
        super.addToTouchbar(jTouchBar, index, config);
        Screen screen = MinecraftClient.getInstance().currentScreen;
        buttons[index] = addButtonToTouchbar(new TranslatableText("widget.mctouchbar.test"), e -> {
            Util.getOperatingSystem().open("https://youtu.be/dQw4w9WgXcQ");
        });
        Random random = new Random();
        i[index] = random.nextInt(colors.length);
//        addSliderToTouchbar(new TranslatableText("widget.mctouchbar.test.slider"), 0, 10, (slider,value) -> {
//        });
//        addTranslatableTextToTouchbar(new TranslatableText("text.mctouchbar.fortnite"));
//        TouchBarButton button = addButtonToTouchbar(new TranslatableText("a" ),e -> {
//            MCTouchbar.LOGGER.info(epic);
//        });
    }
}
