package com.github.zarzelcow.legacylwjgl3.mixin;

import io.github.pumpkinxd.legacylwjgl3.compatibility.environment.GlContextInfo;
import io.github.pumpkinxd.legacylwjgl3.compatibility.workarounds.nvidia.NvidiaWorkarounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import static io.github.pumpkinxd.legacylwjgl3.compatibility.environment.probe.GraphicsAdapterProbe.findAdapters;
import static io.github.pumpkinxd.legacylwjgl3.compatibility.workarounds.nvidia.NvidiaWorkarounds.applyEnvironmentChanges;
import static io.github.pumpkinxd.legacylwjgl3.compatibility.workarounds.nvidia.NvidiaWorkarounds.undoEnvironmentChanges;

@Mixin(Display.class)
public class MixinDisplayApplyNvidiaWorkarounds {
    @Unique
    private static final Logger LOGGER = LogManager.getLogger("LegacyLwjgl3-NvidiaWorkaroundsMixin");

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
    private static void before_glfwCreateWindow(CallbackInfo ci){
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
    private static void after_glfwCreateWindow(CallbackInfo ci){
        undoEnvironmentChanges();
    }
    @Inject(
            method = "create(Lorg/lwjgl/opengl/PixelFormat;)V",
            at= @At(
                    value = "INVOKE",
                    target="Lorg/lwjgl/opengl/GL;createCapabilities()Lorg/lwjgl/opengl/GLCapabilities;",
                    shift = At.Shift.AFTER
            ),
            remap = false
    )
    private static void after_createCapabilities(CallbackInfo ci){
        GlContextInfo context = GlContextInfo.create();
        LOGGER.info("OpenGL Vendor: {}", context.vendor());
        LOGGER.info("OpenGL Renderer: {}", context.renderer());
        LOGGER.info("OpenGL Version: {}", context.version());

        NvidiaWorkarounds.applyContextChanges(context);
    }
}
