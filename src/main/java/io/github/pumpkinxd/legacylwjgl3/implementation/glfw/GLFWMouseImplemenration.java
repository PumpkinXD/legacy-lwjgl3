package io.github.pumpkinxd.legacylwjgl3.implementation.glfw;

import com.github.zarzelcow.legacylwjgl3.implementation.input.MouseImplementation;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

/***
 * @author PumpkinXD
 */
public class GLFWMouseImplemenration  implements MouseImplementation {
    @Override
    public void createMouse() {

    }

    @Override
    public void destroyMouse() {

    }

    @Override
    public void pollMouse(DoubleBuffer coord_buffer, ByteBuffer buttons_buffer) {

    }

    @Override
    public void readMouse(ByteBuffer readBuffer) {

    }

    @Override
    public void setCursorPosition(double x, double y) {

    }

    @Override
    public void grabMouse(boolean grab) {

    }

    @Override
    public boolean hasWheel() {
        return false;
    }

    @Override
    public int getButtonCount() {
        return 0;
    }

    @Override
    public boolean isInsideWindow() {
        return false;
    }
}
