package com.hyper1423.somestuff.util.handlers;

import com.hyper1423.somestuff.tileentities.TileEntityAlloyingMachine;
import com.hyper1423.somestuff.tileentities.TileEntityBlastFurnace;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, new ResourceLocation(Reference.MOD_ID + ":blast_furnace"));
		GameRegistry.registerTileEntity(TileEntityAlloyingMachine.class, new ResourceLocation(Reference.MOD_ID + ":alloying_machine"));
	}
}
