package biscuit;

import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticChecker {

	public static boolean biscuitTopHat(AbstractClientPlayer entitylivingbaseIn) {
		if(entitylivingbaseIn.getName().equals("thebiscuit2010")) {
			return true;
		}
		
		else return false;
	}
}
