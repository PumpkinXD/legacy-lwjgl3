package com.github.zarzelcow.legacylwjgl3.mixin;

import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.pumpkinxd.legacylwjgl3.compatibility.environment.probe.GraphicsAdapterProbe.findAdapters;
import static io.github.pumpkinxd.legacylwjgl3.compatibility.workarounds.nvidia.NvidiaWorkarounds.applyEnvironmentChanges;
import static io.github.pumpkinxd.legacylwjgl3.compatibility.workarounds.nvidia.NvidiaWorkarounds.undoEnvironmentChanges;

@Mixin(Display.class)
public class MixinDisplayApplyNvidiaWorkarounds {
    /***
     * @author pumpkin
     * @reason Disabling Broken NVIDIA Optimization
     */
    @Inject(
            method = "create(Lorg/lwjgl/opengl/PixelFormat;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"
            ),
            remap = false
    )
    private static void beforeglfwCreateWindow(CallbackInfo ci){
        findAdapters();
        applyEnvironmentChanges();
    }

    @Inject(
            method = "create(Lorg/lwjgl/opengl/PixelFormat;)V",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"
            ),
            remap = false
    )
    private static void afterglfwCreateWindow(CallbackInfo ci){
        undoEnvironmentChanges();
    }
}
