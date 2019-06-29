package com.hyper1423.somestuff.util.compat;

import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModItems;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat {

	public static void registerOres() {
		OreDictionary.registerOre("oreCopper", ModBlocks.COPPER_ORE);
		OreDictionary.registerOre("ingotCopper", ModItems.COPPER_INGOT);
		OreDictionary.registerOre("blockCopper", ModBlocks.COPPER_BLOCK);
		OreDictionary.registerOre("plateCopper", ModItems.COPPER_PLATE);
		OreDictionary.registerOre("hammerCopper", ModItems.COPPER_HAMMER);
	}
}
