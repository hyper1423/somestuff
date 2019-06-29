package com.hyper1423.somestuff.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	
	public static void init() {
		GameRegistry.addSmelting(Items.SLIME_BALL, new ItemStack(ModItems.PLASTIC_INGOT, 1), 1F);
		GameRegistry.addSmelting(ModBlocks.PLASTIC_BLOCK, new ItemStack(Blocks.DIAMOND_BLOCK, 1), 1F);
		GameRegistry.addSmelting(Blocks.DIAMOND_BLOCK, new ItemStack(Blocks.AIR, 1), 1F);
		GameRegistry.addSmelting(ModBlocks.COPPER_ORE, new ItemStack(ModItems.COPPER_INGOT), 1F);
		
	}
}
