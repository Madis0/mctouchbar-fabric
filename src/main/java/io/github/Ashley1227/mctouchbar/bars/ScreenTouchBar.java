package io.github.Ashley1227.mctouchbar.bars;

import com.thizzer.jtouchbar.JTouchBar;
import io.github.Ashley1227.mctouchbar.TouchBarManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFWNativeCocoa;

public abstract class ScreenTouchBar {
    protected JTouchBar touchBar;
    protected MinecraftClient client;
    protected Screen minecraftScreen;


    public ScreenTouchBar(Screen screen) {
        this.touchBar = new JTouchBar();
        this.client = MinecraftClient.getInstance();
        this.minecraftScreen = screen;
    }

    public void show() {
        this.touchBar.show(GLFWNativeCocoa.glfwGetCocoaWindow(TouchBarManager.getHandle()));
    }
}
