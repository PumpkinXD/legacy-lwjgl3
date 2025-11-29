package io.github.pumpkinxd.legacylwjgl3.platform.windows.api;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import org.lwjgl.system.Platform;


/**
 * JNA version of
 * <a href="https://github.com/CaffeineMC/sodium/blob/3af8680fd14e4c8bd8f8f5eadf711ca20ce980cb/common/src/workarounds/java/net/caffeinemc/mods/sodium/client/platform/windows/api/Kernel32.java">...</a>
 */

public interface Kernel32 {
    static final int MAX_PATH = 32767;

    static final int GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT = 1 << 0;
    static final int GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS = 1 << 2;

    Pointer GetCommandLineW();//returns wchar_t*(win32 ver.)
    boolean SetEnvironmentVariableW(WString lpName, WString lpValue);
    boolean GetModuleHandleExW(int dwflag, WString lpModuleName, Pointer phModulephModule);
    int GetLastError();
    int GetModuleFileNameW(Pointer hModule, Pointer lpFilename,int nSize);

    Kernel32 INSTANCE = (Platform.get()==Platform.WINDOWS) ? ((Kernel32) Native.loadLibrary("kernel32", Kernel32.class)) : null;
}
