package biscuit.mods;

import biscuit.gui.hud.HUDManager;
import biscuit.mods.impl.ModCPS;
import biscuit.mods.impl.ModFPS;
import biscuit.mods.impl.ModIGN;

public class ModInstances {
	
	private static ModCPS modCPS;
	private static ModIGN modIGN;
	private static ModFPS modFPS;
	
	public static void register(HUDManager api) {
		modCPS = new ModCPS();
		api.register(modCPS);
		
		modIGN = new ModIGN();
		api.register(modIGN);
		
		modFPS = new ModFPS();
		api.register(modFPS);
	}
	
	public static ModCPS getModCPS() {
		return modCPS;
	}
	
	public static ModIGN getModIGN() {
		return modIGN;
	}
	
	public static ModFPS getModFPS() {
		return modFPS;
	}
}
