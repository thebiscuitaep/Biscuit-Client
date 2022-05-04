package biscuit.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import biscuit.utils.FontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class SplashProgress {

	private static final int MAX = 9;
	private static int PROGRESS = 0;
	private static String CURRENT = "";
	private static ResourceLocation splash;
	private static FontUtils font;
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static void update() {
		if(mc == null || mc.getLanguageManager() == null) {
			return;
		}
		drawSplash(mc.getTextureManager());
	}
	
	public static void setProgress(int givenProgress, String givenText) {
		PROGRESS = givenProgress;
		CURRENT = givenText;
		update();
	}
	
	public static void drawSplash(TextureManager tm) {
		ScaledResolution scaledResolution = new ScaledResolution(mc);
		int scaleFactor = scaledResolution.getScaleFactor();
		
		Framebuffer framebuffer = new Framebuffer(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor, true);
		framebuffer.bindFramebuffer(false);
		
		GlStateManager.matrixMode(GL11.GL_PROJECTION);
		GlStateManager.loadIdentity();
		GlStateManager.ortho(0.0D, (double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		GlStateManager.loadIdentity();
		GlStateManager.translate(0.0F, 0.0F, -2000.0F);
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		GlStateManager.disableDepth();
		GlStateManager.enableTexture2D();
		
		if(splash == null) {
			splash = new ResourceLocation("client/mainmenubg1.jpg");
		}
		
		tm.bindTexture(splash);
		
		GlStateManager.resetColor();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 1920, 1080);
		drawProgress();
		framebuffer.unbindFramebuffer();
		framebuffer.framebufferRender(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor);
		
		GlStateManager.enableAlpha();
		GlStateManager.alphaFunc(516, 0.1F);
		
		mc.updateDisplay();
	}
	
	private static void drawProgress() {
		
		if(mc.gameSettings == null || mc.getTextureManager() == null) {
			return;
		}
		
		if(font == null) {
		    font = new FontUtils("Arial", java.awt.Font.PLAIN, 20);
		}
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		double nProgress = (double)PROGRESS;
		double calc = (nProgress / MAX) * sr.getScaledWidth();
		
		Gui.drawRect(0, sr.getScaledHeight() - 35, sr.getScaledWidth(), sr.getScaledHeight(), new Color(0, 0, 0, 50).getRGB());
		
		GlStateManager.resetColor();
		resetTextureState();
		
		font.drawString(CURRENT, 20, sr.getScaledHeight() - 25, 0xFFFFFFFF);
		
		String step = PROGRESS + "/" + MAX;
		font.drawString(step, sr.getScaledWidth() - 20 - font.getWidth(step), sr.getScaledHeight() - 25, 0xe1e1e1FF);
		
		GlStateManager.resetColor();
		resetTextureState();
		
		Gui.drawRect(0, sr.getScaledHeight() - 2, (int)calc, sr.getScaledHeight(), new Color(149, 201, 144).getRGB());
		
		Gui.drawRect(0, sr.getScaledHeight() - 2, sr.getScaledWidth(), sr.getScaledHeight(), new Color(0, 0, 0, 10).getRGB());
	}
	
	private static void resetTextureState() {
		GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
	}
	
}