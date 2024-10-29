package com.github.zarzelcow.legacylwjgl3.mixin;

import com.github.zarzelcow.legacylwjgl3.LegacyLWJGL3;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Screen.class)
public class MixinScreenFixClipboard {


	/**
	 * @author moehreag
	 * @reason Fix clipboard access with GLFW
	 */
	@Overwrite
	public static String getClipboard(){
			String clipboard;
			clipboard = GLFW.glfwGetClipboardString(Display.getHandle());
			if(clipboard == null)
				clipboard="";
			return clipboard;
	}

	/**
	 * @author moehreag
	 * @reason Fix clipboard access with GLFW
	 */
	@Overwrite
	public static void setClipboard(String string){
		GLFW.glfwSetClipboardString(Display.getHandle(), string);
	}
}
