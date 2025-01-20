package com.github.zarzelcow.legacylwjgl3.mixin;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MixinMinecraftFixFullscreenResize {

    @Shadow private boolean fullscreen;

    @Redirect(method = "updateWindow", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;fullscreen:Z"))
    private boolean noFullscreenCheckForResize(MinecraftClient instance) {
        return GLFW.glfwGetPlatform() == GLFW.GLFW_PLATFORM_WIN32 && fullscreen;
    }
}
