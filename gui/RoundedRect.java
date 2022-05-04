package biscuit.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoundedRect
/*    */ {
/*    */   public static void drawRoundedRect(int x, int y, float f, float g, int cornerRadius, Color color) {
/* 15 */     Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + g - cornerRadius, color.getRGB());
/* 16 */     Gui.drawRect(x + cornerRadius, y, x + f - cornerRadius, y + g, color.getRGB());
/* 17 */     Gui.drawRect(x + f - cornerRadius, y + cornerRadius, x + f, y + g - cornerRadius, color.getRGB());
/*    */     
/* 19 */     drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
/* 20 */     drawArc(x + f - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
/* 21 */     drawArc(x + f - cornerRadius, y + g - cornerRadius, cornerRadius, 180, 270, color);
/* 22 */     drawArc(x + cornerRadius, y + g - cornerRadius, cornerRadius, 90, 180, color);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void drawArc(float g, float f, int radius, int startAngle, int endAngle, Color color) {
/* 27 */     GL11.glPushMatrix();
/* 28 */     GL11.glEnable(3042);
/* 29 */     GL11.glDisable(3553);
/* 30 */     GL11.glBlendFunc(770, 771);
/* 31 */     GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/*    */     
/* 33 */     WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
/*    */     
/* 35 */     worldRenderer.begin(6, DefaultVertexFormats.POSITION);
/* 36 */     worldRenderer.pos(g, f, 0.0D).endVertex();
/*    */     
/* 38 */     for (int i = (int)(startAngle / 360.0D * 100.0D); i <= (int)(endAngle / 360.0D * 100.0D); i++) {
/* 39 */       double angle = 6.283185307179586D * i / 100.0D + Math.toRadians(180.0D);
/* 40 */       worldRenderer.pos(g + Math.sin(angle) * radius, f + Math.cos(angle) * radius, 0.0D).endVertex();
/*    */     } 
/*    */     
/* 43 */     Tessellator.getInstance().draw();
/*    */     
/* 45 */     GL11.glEnable(3553);
/* 46 */     GL11.glDisable(3042);
/* 47 */     GL11.glPopMatrix();
/*    */   }
/*    */
public static void drawRoundedRectInt(int x, int y, int f, int g, int cornerRadius, Color color) {
	/* 15 */     Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + g - cornerRadius, color.getRGB());
	/* 16 */     Gui.drawRect(x + cornerRadius, y, x + f - cornerRadius, y + g, color.getRGB());
	/* 17 */     Gui.drawRect(x + f - cornerRadius, y + cornerRadius, x + f, y + g - cornerRadius, color.getRGB());
	/*    */     
	/* 19 */     drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
	/* 20 */     drawArc(x + f - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
	/* 21 */     drawArc(x + f - cornerRadius, y + g - cornerRadius, cornerRadius, 180, 270, color);
	/* 22 */     drawArc(x + cornerRadius, y + g - cornerRadius, cornerRadius, 90, 180, color);
	
} }


/* Location:              C:\Users\Lenovo\Downloads\ZyrexClientV1 (1).jar!\ZyrexClient\RoundedRect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */