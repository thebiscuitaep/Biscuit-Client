package biscuit.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import biscuit.gui.hud.IRenderer;
import biscuit.gui.hud.ScreenPosition;
import biscuit.mods.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class ModCPS extends ModDraggable {
	
	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;
	private ScreenPosition pos;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("CPS: " + getCPS());
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}
	
	@Override
	public void render(ScreenPosition pos) {
		
		final boolean pressed = Mouse.isButtonDown(0);
		
		if(pressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = pressed;
			if(pressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		
		Gui.drawRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 170).getRGB());
		font.drawString("CPS: " + getCPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("CPS: " + getCPS(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}
	
	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
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