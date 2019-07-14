package com.hyper1423.somestuff.util.handlers;

import com.hyper1423.somestuff.init.ModFluids;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

public class RenderHandler {
	public static void registerCustomMeshesAndStates() {
		for (BlockFluidBase fluid : ModFluids.BLOCKFLUIDS) {
			ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid), stack -> new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, fluid.getFluid().getName()), "fluid"));
			ModelLoader.setCustomStateMapper(fluid, new StateMapperBase() {
				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					
					return new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, fluid.getFluid().getName()), "fluid");
				}
			});
		}
	}
}
