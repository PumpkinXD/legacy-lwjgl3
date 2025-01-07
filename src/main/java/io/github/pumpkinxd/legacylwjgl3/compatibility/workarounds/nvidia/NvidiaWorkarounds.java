package io.github.pumpkinxd.legacylwjgl3.compatibility.workarounds.nvidia;

import com.sun.jna.Native;
import io.github.pumpkinxd.legacylwjgl3.compatibility.env.probe.GraphicsAdapterProbe;
import io.github.pumpkinxd.legacylwjgl3.compatibility.env.probe.GraphicsAdapterVendor;
import io.github.pumpkinxd.legacylwjgl3.util.POSIX_Func_setenv;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/***
 * Implementation from Sodium
 * https://github.com/CaffeineMC/sodium/blob/3af8680fd14e4c8bd8f8f5eadf711ca20ce980cb/common/src/workarounds/java/net/caffeinemc/mods/sodium/client/compatibility/workarounds/nvidia/NvidiaWorkarounds.java
 */


//disabling threaded optimizations on nvidia cards
public class NvidiaWorkarounds {
    private static final Logger LOGGER = LogManager.getLogger();


    public static boolean isNvidiaGraphicsCardPresent() {//currently this method only works on linux
        GraphicsAdapterProbe.findAdapters();
        return GraphicsAdapterProbe.getAdapters()
                .stream()
                .anyMatch(adapter -> adapter.vendor() == GraphicsAdapterVendor.NVIDIA);
    }

    public static void applyEnvironmentChanges() {
        if (!isNvidiaGraphicsCardPresent()) return;
        LOGGER.warn("Applying workaround: Prevent NVIDIA OpenGL driver from using broken optimization (NVIDIA_THREADED_OPTIMIZATIONS)");
        if (SystemUtils.IS_OS_LINUX) {
            try {
                var func_setenv_delegate = (POSIX_Func_setenv) Native.loadLibrary("c", POSIX_Func_setenv.class);
                func_setenv_delegate.setenv("__GL_THREADED_OPTIMIZATIONS", "0", 1);
            } catch (Exception e) {
                LOGGER.error("Failed to apply NVIDIA workaround", e);
                LOGGER.error("READ ME! The workaround for the NVIDIA Graphics Driver did not apply correctly!");
                LOGGER.error("READ ME! Your game is highly likely to crash at startup");
            }

        } else if (SystemUtils.IS_OS_WINDOWS) {
            LOGGER.warn("NVIDIA workaround is not implemented on Windows yet, nothing applied.");
            //TODO:port sodium's impl
        } else if (SystemUtils.IS_OS_MAC) {
            LOGGER.error("uh... how you installed a NVIDIA Graphic Card on a MAC? Hackintosh?");
            //Hackintosh with NVIDIA card???
        } else if (SystemUtils.IS_OS_UNIX) {
            //other *NIX with NVIDIA card???
        }
    }
    public static void undoEnvironmentChanges(){
        if(!isNvidiaGraphicsCardPresent()) return;
        if (SystemUtils.IS_OS_WINDOWS) {
            //TODO:port sodium's impl
            /** soon **/}
    }
}
