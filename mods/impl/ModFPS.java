package biscuit.mods.impl;

import java.awt.Color;

import biscuit.gui.hud.ScreenPosition;
import biscuit.mods.ModDraggable;
import net.minecraft.client.gui.Gui;

public class ModFPS extends ModDraggable {

	private ScreenPosition pos;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("FPS: " + mc.getDebugFPS());
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 170).getRGB());
		font.drawString("FPS: " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("FPS: " + mc.getDebugFPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}

}
