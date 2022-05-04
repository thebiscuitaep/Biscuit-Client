package biscuit.utils;

import java.awt.Color;

public class ChromaRGBUtil {
	public static int getRainbow(float seconds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % 4000) / 4000f;
		int chromaRGB = Color.HSBtoRGB(hue, saturation, brightness);
		return chromaRGB;
	}
	
	public static int getRainbowWave(float seconds, float saturation, float brightness, long index) {
		float hue = ((System.currentTimeMillis() + index) % (int)(seconds * 1000)) / (float)(seconds * 1000f);
		int chromaRGB = Color.HSBtoRGB(hue, saturation, brightness);
		return chromaRGB;
	}
}
