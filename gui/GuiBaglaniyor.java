package biscuit.gui;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.lwjgl.input.Keyboard;

import biscuit.Client;
import biscuit.utils.FontUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

public class GuiBaglaniyor extends GuiScreen {
    public static FontUtils font1 = new FontUtils("Arial", java.awt.Font.PLAIN, 24);
    public static FontUtils font1BOLD = new FontUtils("Arial", java.awt.Font.BOLD, 24);
    private final GuiScreen previousScreen;
    ScaledResolution sr;
    int j = this.height / 4 + 48;

    public GuiBaglaniyor() {
        this.previousScreen = (GuiScreen)null;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 5: {
                GuiConnecting.connect(Client.serverIP, 25565);
                break;
            }
            
            case 10: {
                this.mc.shutdown();
            }
        }
    }
  
    
    @Override
    public void drawScreen(int x2, int y2, float z2) {
		float hue = (System.currentTimeMillis() % 4000) / 4000f;
		int chromaRGB = Color.HSBtoRGB(hue, 1, 1);
        
        this.drawDefaultBackground();
        font1.drawCenteredString("Connecting to server...", width / 2, height / 2, Color.white.getRGB());
        super.drawScreen(x2, y2, z2);
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(5, width / 2 - 80, height / 2 + 20 , 170, 20, I18n.format("Doesn't Connect? Try Again", new Object[0])));
        this.buttonList.add(new GuiButton(10, width / 2 - 80, height / 2 + 45 , 170, 20, I18n.format("Quit Game", new Object[0])));
        int var3 = height / 4 + 24;
    }

    @Override
    public void onGuiClosed() {

    }

    @Override
    public void updateScreen() {

    }
}
