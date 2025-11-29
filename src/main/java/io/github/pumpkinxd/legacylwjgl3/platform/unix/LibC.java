package io.github.pumpkinxd.legacylwjgl3.platform.unix;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.lwjgl.system.Platform;

public interface LibC extends Library {
    LibC INSTANCE = (Platform.get()==Platform.LINUX||Platform.get()==Platform.FREEBSD) ? ((LibC) Native.loadLibrary("c", LibC.class)) : null;

    //https://pubs.opengroup.org/onlinepubs/9799919799/functions/setenv.html
    int setenv(String envname, String envval, boolean overwrite);
}
