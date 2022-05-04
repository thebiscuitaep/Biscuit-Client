package biscuit;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import com.mojang.realmsclient.client.FileDownload;

import biscuit.event.EventManager;
import biscuit.event.EventTarget;
import biscuit.event.impl.ClientTickEvent;
import biscuit.gui.DiscordRP;
import biscuit.gui.SplashProgress;
import biscuit.gui.hud.HUDManager;
import biscuit.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;

public class Client {

	private static final Client INSTANCE = new Client();
	public static String serverIP = "play.knightnw.com";
	public static final Client getInstance() {	
		return INSTANCE; 
	}
	
	private HUDManager hudManager;
	private DiscordRP discordRP = new DiscordRP();
	private Minecraft mc = Minecraft.getMinecraft();

	public void init() throws MalformedURLException, IOException {
		EventManager.register(this);
		SplashProgress.setProgress(1, "Client - Initalising Discord RP!");
		discordRP.start();
		//ImageDownloader.DownloadImage("C:/Users/alpel/Desktop/Biscuit Client/src/minecraft/assets/minecraft/client/" + mc.getSession().getUsername() + ".png" ,"https://cravatar.eu/avatar/" + mc.getSession().getUsername() + "/64.png");
	}
	
	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
	}
	
	public void shutdown() {
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
	@EventTarget
	public void onTick(ClientTickEvent e) {
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			hudManager.openConfigScreen();
		}
	}
	
	public static void addChatMessage(String s) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Biscuit: " + s));
	}
	
}
