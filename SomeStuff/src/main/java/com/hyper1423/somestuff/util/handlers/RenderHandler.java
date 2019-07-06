package com.hyper1423.somestuff.util.handlers;

import com.hyper1423.somestuff.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler {
	public static void registerCustomMeshesAndStates() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.BLOCK_MOLTEN_IRON),
				new ItemMeshDefinition() {
					@Override
					public ModelResourceLocation getModelLocation(ItemStack stack) {
						// TODO Auto-generated method stub
						return new ModelResourceLocation(new ResourceLocation("somestuff", "molten_iron"), "fluid");
					}
				});
		ModelLoader.setCustomStateMapper(ModBlocks.BLOCK_MOLTEN_IRON, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				// TODO Auto-generated method stub
				return new ModelResourceLocation(new ResourceLocation("somestuff", "molten_iron"), "fluid");
			}
		});
	}
}
