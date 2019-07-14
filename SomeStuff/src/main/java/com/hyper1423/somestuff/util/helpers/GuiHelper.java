package com.hyper1423.somestuff.util.helpers;

import static org.lwjgl.opengl.GL11.GL_QUADS;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import com.hyper1423.somestuff.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public final class GuiHelper {
	public static void fillRectWithIcon(TextureAtlasSprite icon, int x, int y, int width, int height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder b = tessellator.getBuffer();
		b.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		float z = getZLevel();
		
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();
		
		int imageCols = width / iconWidth;
		int imageRows = height / iconHeight;
		
		float minU = icon.getMinU();
		float minV = icon.getMinV();
		float maxU = icon.getMaxU();
		float maxV = icon.getMaxV();
		
		int excessWidth = width % iconWidth;
		int excessHeight = height % iconHeight;
		
		float partialMaxU = maxU * ((float) excessWidth / iconWidth);
		float partialMaxV = maxV * ((float) excessHeight / iconHeight);
		
		int xNow;
		int yNow;
		for (int row = 0; row < imageRows; row++) {
			yNow = y + row * iconHeight;
			for (int col = 0; col < imageCols; col++) {
				// Draw only full icons
				xNow = x + col * iconWidth;
				drawRect(xNow, yNow, iconWidth, iconHeight, z, minU, minV, maxU, maxV);
			}
			if (excessWidth != 0) {
				// Fill empty icons on the right side
				xNow = x + imageCols * iconWidth;
				drawRect(xNow, yNow, iconWidth, iconHeight, z, minU, minV, maxU, maxV);
			}
		}
		if (excessHeight != 0) {
			// Fill empty icons on the bottom
			for (int col = 0; col < imageCols; col++) {
				xNow = x + col * iconWidth;
				yNow = y + imageRows * iconHeight;
				drawRect(xNow, yNow, iconWidth, excessHeight, z, minU, minV, maxU, partialMaxV);
			}
			if (excessWidth != 0) {
				// Fill empty icon on the bottom right corner
				xNow = x + imageCols * iconWidth;
				yNow = y + imageRows * iconHeight;
				drawRect(xNow, yNow, excessWidth, excessHeight, z, minU, minV, partialMaxU, partialMaxV);
			}
		}
		
		tessellator.draw();
	}
	
	private static void drawRect(float x, float y, float width, float height, float z, float u, float v, float maxU, float maxV) {
		BufferBuilder b = Tessellator.getInstance().getBuffer();
		
		b.pos(x, y + height, z).tex(u, maxV).endVertex();
		b.pos(x + width, y + height, z).tex(u, maxV).endVertex();
		b.pos(x + width, y, z).tex(u, maxV).endVertex();
		b.pos(x, y, z).tex(u, maxV).endVertex();
	}

	private static float getZLevel() {
		GuiScreen screen = Minecraft.getMinecraft().currentScreen;
		try {
			return screen == null ? 0 : (float) zLevelGet.invokeExact((Gui)screen);
		} catch (Throwable e) {
			Reference.LOGGER.error("Could not access to the field");
			Reference.LOGGER.catching(e);
			throw new RuntimeException(e);
		}
	}

	private static final MethodHandle zLevelGet;

	static {
		Field field = null;
		try {
			field = Gui.class.getDeclaredField(ObfuscatedNameHelper.getFieldObfName("zLevel", Gui.class));
			field.setAccessible(true);
		} catch (NoSuchFieldException e) {
			Reference.LOGGER.error("There is no such field");
			Reference.LOGGER.catching(e);
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			Reference.LOGGER.error("Could not access to the field");
			Reference.LOGGER.catching(e);
			throw new RuntimeException(e);
		} finally {
			if (field == null) {
				NoSuchFieldException e = new NoSuchFieldException();
				Reference.LOGGER.error("Cannot find field");
				Reference.LOGGER.catching(e);
				throw new RuntimeException(e);
			}
			try {
				zLevelGet = MethodHandles.publicLookup().unreflectGetter(field);
			} catch (IllegalAccessException e) {
				Reference.LOGGER.error("Could not access to the field");
				Reference.LOGGER.catching(e);
				throw new RuntimeException(e);
			}
		}
	}
}
