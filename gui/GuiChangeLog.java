package biscuit.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import biscuit.utils.FontUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

public class GuiChangeLog extends GuiScreen {
    public static FontUtils font1 = new FontUtils("Arial", java.awt.Font.PLAIN, 24);
    public static FontUtils font1BOLD = new FontUtils("Arial", java.awt.Font.BOLD, 24);
    private final GuiScreen previousScreen;
    ScaledResolution sr;
    int j = this.height / 4 + 48;

    public GuiChangeLog(GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen(this.previousScreen);
                break;
            }
        }
    }

    @Override
    public void drawScreen(int x2, int y2, float z2) {
		float hue = (System.currentTimeMillis() % 4000) / 4000f;
		int chromaRGB = Color.HSBtoRGB(hue, 1, 1);
    	
        this.drawDefaultBackground();
        font1BOLD.drawCenteredString("Change Log", width / 2, 25, chromaRGB);
        font1BOLD.drawCenteredString("30/07/2021", width / 2, 40, -1);
        font1BOLD.drawCenteredString("Biscuit v1", width / 2, 55, chromaRGB);
        
        font1.drawCenteredString("Added Change Log", width / 2, 85, -1);
        font1.drawCenteredString("Added Main Menu IGN", width / 2, 100, -1);
        font1.drawCenteredString("Added Main Menu Logo", width / 2, 115, -1);
        font1.drawCenteredString("Added Draggable HUD", width / 2, 130, -1);
        font1.drawCenteredString("Added CPS Mod", width / 2, 145, -1);
        font1.drawCenteredString("Added IGN Mod", width / 2, 160, -1);
        font1.drawCenteredString("Added Login", width / 2, 175, -1);
        font1.drawCenteredString("Added New FontRenderer", width / 2, 190, -1);
        font1.drawCenteredString("Added Discord RP", width / 2, 205, -1);
        font1.drawCenteredString("Added Splash Screen", width / 2, 220, -1);
        super.drawScreen(x2, y2, z2);
    }

    @Override
    public void initGui() {
        int var3 = height / 4 + 24;
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 2 + 72 + 25, I18n.format("Back", new Object[0])));
    }

    @Override
    public void onGuiClosed() {
        
    }

    @Override
    public void updateScreen() {

    }
}
