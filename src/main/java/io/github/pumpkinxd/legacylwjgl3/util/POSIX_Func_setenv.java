package io.github.pumpkinxd.legacylwjgl3.util;


//https://pubs.opengroup.org/onlinepubs/9799919799/
public interface POSIX_Func_setenv extends com.sun.jna.Library{
//    POSIX_setenv_Func instance= (POSIX_setenv_Func)Native.loadLibrary("c", POSIX_setenv_Func.class);
    int setenv(String envname, String envval, int overwrite);

}
