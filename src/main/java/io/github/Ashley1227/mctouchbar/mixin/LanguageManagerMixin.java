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
import java.util.stream.Stream;

@Mixin(LanguageManager.class)
public class LanguageManagerMixin {

	//@Inject(at = @At("TAIL"), method = "Lnet/minecraft/client/resource/language/LanguageManager;loadAvailableLanguages(Ljava/util/stream/Stream;)Ljava/util/Map")
	private void constructor(Stream<ResourcePack> list, CallbackInfo ci) {
		if(MCTouchbar.isMac)
			TouchBarManager.regenTouchbar();
	}
}
