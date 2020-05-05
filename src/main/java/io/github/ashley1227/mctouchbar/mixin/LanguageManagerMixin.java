package io.github.Ashley1227.mctouchbar.mixin;

import io.github.Ashley1227.mctouchbar.MCTouchbar;
import io.github.Ashley1227.mctouchbar.TouchBarManager;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LanguageManager.class)
public class LanguageManagerMixin {

	@Inject(at = @At("TAIL"), method = "Lnet/minecraft/client/resource/language/LanguageManager;reloadResources(Ljava/util/List;)V")
	private void constructor(List<ResourcePack> list, CallbackInfo ci) {
		if(MCTouchbar.isMac)
			TouchBarManager.regenTouchbar();
	}
}
