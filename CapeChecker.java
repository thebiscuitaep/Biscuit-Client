package biscuit;

import net.minecraft.client.entity.AbstractClientPlayer;

public class CapeChecker {

	public static boolean biscuitCape(AbstractClientPlayer entitylivingbaseIn) {
		if(entitylivingbaseIn.getName().equals("thebiscuit2010")) {
			return true;
		}
		
		else return false;
	}
	

	public static boolean alyaCape(AbstractClientPlayer entitylivingbaseIn) {
		if(entitylivingbaseIn.getName().equals("Alya_Strafe")) {
			return true;
		}
		
		else return false;
	}
}
