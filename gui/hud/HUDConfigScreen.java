package biscuit.gui.hud;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import org.lwjgl.input.Keyboard;

import biscuit.utils.ChromaRGBUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class HUDConfigScreen extends GuiScreen {

	private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();
	
	private Optional<IRenderer> selectedRenderer = Optional.empty();
	
	private int prevX, prevY;
	
	public HUDConfigScreen(HUDManager api) {

		Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();
		
		for(IRenderer ren : registeredRenderers) {
			if(!ren.isEnabled()) {
				continue;
			}
			
			ScreenPosition pos = ren.load();
			
			if(pos == null) {
				pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
			}
			
			adjustBounds(ren, pos);
			this.renderers.put(ren, pos);
		}
		
		this.buttonList.add(new GuiButton(0, this.width/2 - 100, this.height / 2 - 20, 100, 20, "Toggle Mods"));

	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		super.drawDefaultBackground();

		final float zBackup = this.zLevel;
		this.zLevel = 200;
		
		this.drawHollowRect(0, 0, this.width - 1, this.height - 1, ChromaRGBUtil.getRainbow(8, 1, 1));
		
		for(IRenderer renderer : renderers.keySet()) {
			
			ScreenPosition pos = renderers.get(renderer);
			
			renderer.renderDummy(pos);
			
			this.drawHollowRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, renderer.getWidth() + 2, renderer.getHeight() + 2, 0xFF00FFFF);
			
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.zLevel = zBackup;
	}

	private void drawHollowRect(int x, int y, int w, int h, int color) {
		this.drawHorizontalLine(x, x + w, y, color);
		this.drawHorizontalLine(x, x + w, y + h, color);
		
		this.drawVerticalLine(x, y + h, y, color);
		this.drawVerticalLine(x + w, y + h, y, color);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == Keyboard.KEY_ESCAPE) {
			renderers.entrySet().forEach((entry) -> {
				entry.getKey().save(entry.getValue());
			});
			this.mc.displayGuiScreen(null);
		}
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) {
		if(selectedRenderer.isPresent()) {
			moveSelectedRenderBy(x - prevX, y - prevY);
		}
		
		this.prevX = x;
		this.prevY = y;
		super.mouseClickMove(x, y, button, time);
	}

	private void moveSelectedRenderBy(int offsetX, int offsetY) {
		IRenderer renderer = selectedRenderer.get();
		ScreenPosition pos = renderers.get(renderer);
		
		pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);
		
		adjustBounds(renderer, pos);
	}
	
	@Override
	public void onGuiClosed() {
		
		for(IRenderer renderer : renderers.keySet()) {
			renderer.save(renderers.get(renderer));
		}
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	private void adjustBounds(IRenderer renderer, ScreenPosition pos) {
		
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();
		
		int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
		int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));
		
		pos.setAbsolute(absoluteX, absoluteY);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int mobuttonuseButton) {
		this.prevX = x;
		this.prevY = y;
		
		loadMouseOver(x, y);
		super.mouseClicked(x, y, mobuttonuseButton);
	}

	private void loadMouseOver(int x, int y) {
		this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
	}
	
	private class MouseOverFinder implements Predicate<IRenderer> {

		private int mouseX, mouseY;
		
		public MouseOverFinder(int x, int y) {
			this.mouseX = x;
			this.mouseY = y;
		}

		@Override
		public boolean test(IRenderer renderer) {

			ScreenPosition pos = renderers.get(renderer);
			
			int absoluteX = pos.getAbsoluteX();
			int absoluteY = pos.getAbsoluteY();
			
			if(mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {
				
				if(mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
					
					return true;
					
				}
				
			}

			return false;
		}
		
	}
	
	
}