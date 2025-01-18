package com.github.zarzelcow.legacylwjgl3.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MixinMinecraftFixFullscreenResize {

    @Redirect(method = "updateWindow", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;fullscreen:Z"))
    private boolean noFullscreenCheckForResize(MinecraftClient instance) {
        return false;
    }
}
