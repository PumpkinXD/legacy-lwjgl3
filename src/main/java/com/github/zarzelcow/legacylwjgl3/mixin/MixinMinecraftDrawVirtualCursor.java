package com.github.zarzelcow.legacylwjgl3.mixin;

import io.github.moehreag.legacylwjgl3.implementation.glfw.VirtualGLFWMouseImplementation;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftDrawVirtualCursor {

	@Inject(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/AchievementNotification;tick()V"))
	private void drawVirtualCursor(CallbackInfo ci) {
		VirtualGLFWMouseImplementation.render();
	}
}
