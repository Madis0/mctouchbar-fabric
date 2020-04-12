package io.github.Ashley1227.mctouchbar.mixin;

import io.github.Ashley1227.mctouchbar.MCTouchbar;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LanguageManager.class)
public class LanguageManagerMixin {

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/client/resource/language/LanguageManager;reloadResources(Ljava/util/List;)V")
    private void constructor(List<ResourcePack> list, CallbackInfo ci) {
        MCTouchbar.regenTouchbar();
    }
}
