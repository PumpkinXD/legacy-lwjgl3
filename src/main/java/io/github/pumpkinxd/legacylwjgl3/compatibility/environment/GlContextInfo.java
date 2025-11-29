package io.github.pumpkinxd.legacylwjgl3.compatibility.environment;

import org.lwjgl.opengl.GL11C;

import java.util.Objects;

/***
 * Implementation from Sodium
 * <a href="https://github.com/CaffeineMC/sodium/blob/3af8680fd14e4c8bd8f8f5eadf711ca20ce980cb/common/src/workarounds/java/net/caffeinemc/mods/sodium/client/compatibility/environment/GlContextInfo.java">...</a>
 */

public record GlContextInfo(String vendor, String renderer, String version) {
    public static GlContextInfo create() {
        String vendor = Objects.requireNonNull(GL11C.glGetString(GL11C.GL_VENDOR),
                "GL_VENDOR is NULL");
        String renderer = Objects.requireNonNull(GL11C.glGetString(GL11C.GL_RENDERER),
                "GL_RENDERER is NULL");
        String version = Objects.requireNonNull(GL11C.glGetString(GL11C.GL_VERSION),
                "GL_VERSION is NULL");

        return new GlContextInfo(vendor, renderer, version);
    }
}
