package io.github.Ashley1227.mctouchbar.mixin;

import io.github.Ashley1227.mctouchbar.MCTouchbar;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {

    @Shadow @Final private long handle;

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/client/util/Window;<init>(Lnet/minecraft/client/WindowEventHandler;Lnet/minecraft/client/util/MonitorTracker;Lnet/minecraft/client/WindowSettings;Ljava/lang/String;Ljava/lang/String;)V")
    private void init(CallbackInfo info) {
        if(MCTouchbar.isMac)
            MCTouchbar.onWindowLoad(this.handle);
    }

}
