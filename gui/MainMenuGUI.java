package biscuit.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import biscuit.utils.ChromaRGBUtil;
import biscuit.utils.FontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenuGUI {
	
	public static Minecraft mc = Minecraft.getMinecraft();
    public static FontUtils font1 = new FontUtils("Arial", java.awt.Font.PLAIN, 24);
	
	public static void draw() {
		ScaledResolution sr = new ScaledResolution(mc);

		GlStateManager.pushMatrix();
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(1, 1, 1);
		GlStateManager.translate(-4, -4, 0);
		RoundedRect.drawRoundedRect(2, 3, font1.getWidth("Logged in as " + mc.session.getUsername()) + 5, font1.getHeight("Logged in as " + mc.session.getUsername()) + 3, 2, new Color(0, 0, 0, 100));
		font1.drawString("Logged in as " + mc.session.getUsername(), 4, 4, -1);
		//mc.getTextureManager().bindTexture(new ResourceLocation("C:/Users/alpel/Desktop/Biscuit Client/src/minecraft/assets/minecraft/client/"  + mc.getSession().getUsername() + ".png"));
		//Gui.drawModalRectWithCustomSizedTexture(4, 16, 64, 64, 64, 64, 64, 64);
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(0.25, 0.25, 1);
		GlStateManager.translate(-4, -4, 0);
		GlStateManager.popMatrix();
	}
}
