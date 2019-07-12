package com.hyper1423.somestuff.container.gui;

import com.hyper1423.somestuff.container.ContainerBlastFurnace;
import com.hyper1423.somestuff.tileentities.TileEntityBlastFurnace;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiBlastFurnace extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/blast_furnace.png");
	private final InventoryPlayer player;
	private final TileEntityBlastFurnace tileentity;

	public GuiBlastFurnace(InventoryPlayer player, TileEntityBlastFurnace tileentity) {
		super(new ContainerBlastFurnace(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		if (this.tileentity.getDisplayName() != null) {
			String tileName = this.tileentity.getDisplayName().getUnformattedText();
			this.fontRenderer.drawString(tileName,
					(this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8, 0x404040);
			this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2,
					0x404040);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (TileEntityBlastFurnace.isBurning(tileentity)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 36, this.guiTop + 37 + 12 - k, 176, 12 - k, 14, k + 1);
		}

		int l = this.getCookProgressScaled(22);
		this.drawTexturedModalRect(this.guiLeft + 59, this.guiTop + 32, 176, 14, l - 1, 22);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// TODO Auto-generated method stub
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	private int getBurnLeftScaled(int pixels) {
		int i = this.tileentity.currentBurnTime;
		int j = this.tileentity.burnTime;
		if (i == 0)
			i = 200;
		return j * pixels / i;
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.tileentity.cookTime;
		int j = this.tileentity.totalCookTime;
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
