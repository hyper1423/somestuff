package com.hyper1423.somestuff.container.gui;

import com.hyper1423.somestuff.container.ContainerAlloyingMachine;
import com.hyper1423.somestuff.tileentities.TileEntityAlloyingMachine;
import com.hyper1423.somestuff.tileentities.TileEntityAlloyingMachine.EnumDirection;
import com.hyper1423.somestuff.tileentities.TileEntityAlloyingMachine.IllegalDirectionException;
import com.hyper1423.somestuff.util.Reference;
import com.hyper1423.somestuff.util.helpers.GuiHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class GuiAlloyingMachine extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/alloying_machine.png");
	private final InventoryPlayer player;
	private final TileEntityAlloyingMachine tileentity;

	public GuiAlloyingMachine(InventoryPlayer player, TileEntityAlloyingMachine tileentity) {
		super(new ContainerAlloyingMachine(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
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

		FluidTank fluidTank0 = (FluidTank) this.tileentity
				.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.WEST);
		FluidTank fluidTank1 = (FluidTank) this.tileentity
				.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.EAST);
		FluidTank fluidTank2 = (FluidTank) this.tileentity
				.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
		drawFluid(fluidTank0, this.guiLeft + 6, this.guiTop + 19, 16, 50, true);
		drawFluid(fluidTank1, this.guiLeft + 126, this.guiTop + 19, 16, 50, true);
		drawFluid(fluidTank2, this.guiLeft + 66, this.guiTop + 5, 16, 50, true);

		if (TileEntityAlloyingMachine.isAlloying(tileentity, EnumDirection.LEFT)) {
			int isAlloying = this.getAlloyingProgressScaled(30, EnumDirection.LEFT);
			this.drawTexturedModalRect(this.guiLeft + 28, this.guiTop + 21, 176, 0, isAlloying, 15);
		}
		if (TileEntityAlloyingMachine.isAlloying(tileentity, EnumDirection.RIGHT)) {
			int isAlloying = this.getAlloyingProgressScaled(30, EnumDirection.RIGHT);
			this.drawTexturedModalRect(this.guiLeft + 120 - isAlloying, this.guiTop + 21, 206 + 30 - isAlloying, 0,
					isAlloying, 15);
		}
		if (TileEntityAlloyingMachine.isMelting(tileentity, EnumDirection.LEFT)) {
			int isMelting = this.getMeltingProgressScaled(16, EnumDirection.LEFT);
			this.drawTexturedModalRect(this.guiLeft + 40 - isMelting, this.guiTop + 51, 176 + 16 - isMelting, 15,
					isMelting, 15);
		}
		if (TileEntityAlloyingMachine.isMelting(tileentity, EnumDirection.RIGHT)) {
			int isMelting = this.getMeltingProgressScaled(16, EnumDirection.RIGHT);
			this.drawTexturedModalRect(this.guiLeft + 108, this.guiTop + 51, 192, 15, isMelting, 15);
		}

		int energy = this.getEnergyScaled(40);
		this.drawTexturedModalRect(152, 15 + energy, 176, 30 + energy, 12, energy);
//
//		int l = this.getCookProgressScaled(22);
//		this.drawTexturedModalRect(this.guiLeft + 59, this.guiTop + 32, 176, 14, l - 1, 22);
	}

	private void drawFluid(FluidTank fluidTank, int left, int top, int width, int height, boolean bindTexture) {
		if (fluidTank == null) {
			throw new NullPointerException("Do not try to draw null FluidTank.");
		} else if (fluidTank.getFluid() == null) {
			this.drawRect(left, top, left + width, top + height, /* 0x8B8B8B */0x000000);
			return;
		} else if (fluidTank.getFluid().getFluid() == null) {
			Reference.LOGGER.info("Fluid of given FluidTank is null.");
			return;
		} else if (fluidTank.getFluid().getFluid().getStill() == null) {
			Reference.LOGGER.info("The texture of given Fluid is null. Maybe you forgot to register textures?");
			return;
		}
		
		int tankScaled = this.getFluidTankScaled(height, fluidTank);
		GuiHelper.renderRectWithFluidTank(fluidTank, left, top, this.zLevel, width, height);
		if (bindTexture)
			this.mc.getTextureManager().bindTexture(TEXTURES);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	private int getAlloyingProgressScaled(int pixels, EnumDirection index) {
		int i = this.tileentity.totalAlloyingTime;
		switch (index) {
		case LEFT:
			int l = this.tileentity.alloyingTimeLeft;
			return i != 0 && l != 0 ? l * pixels / i : 0;
		case RIGHT:
			int r = this.tileentity.alloyingTimeRight;
			return i != 0 && r != 0 ? r * pixels / i : 0;
		default:
			throw new IllegalDirectionException("You can only put EnumDirection.LEFT or EnumDirection.RIGHT in index.");
		}
	}

	private int getMeltingProgressScaled(int pixels, EnumDirection index) {
		int i = this.tileentity.totalMeltingTime;
		switch (index) {
		case LEFT:
			int l = this.tileentity.meltingTimeLeft;
			return i != 0 && l != 0 ? l * pixels / i : 0;
		case RIGHT:
			int r = this.tileentity.meltingTimeRight;
			return i != 0 && r != 0 ? r * pixels / i : 0;
		default:
			throw new IllegalDirectionException("You can only put EnumDirection.LEFT or EnumDirection.RIGHT in index.");
		}
	}

	private int getFluidTankScaled(int pixels, FluidTank fluidTank) {
		int i = fluidTank.getFluidAmount();
		int j = fluidTank.getCapacity();
		return i * pixels / j;
	}

	private int getEnergyScaled(int pixels) {
		IEnergyStorage energy = this.tileentity.getCapability(CapabilityEnergy.ENERGY, null);
		int i = energy.getEnergyStored();
		int j = energy.getMaxEnergyStored();
		return i != 0 && j != 0 ? j * pixels / i : 0;
	}
}
