package com.hyper1423.somestuff.init;

import java.util.ArrayList;
import java.util.List;

import com.hyper1423.somestuff.blocks.BlockBlastFurnace;
import com.hyper1423.somestuff.blocks.BlockCopper;
import com.hyper1423.somestuff.blocks.BlockCopperOre;
import com.hyper1423.somestuff.blocks.BlockPlastic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
			
			public static final Block PLASTIC_BLOCK = new BlockPlastic("plastic_block", Material.IRON);
			public static final Block COPPER_ORE = new BlockCopperOre("copper_ore", Material.ROCK);
			public static final Block COPPER_BLOCK = new BlockCopper("copper_block", Material.IRON);
			public static final Block BLAST_FURNACE = new BlockBlastFurnace("blast_furnace", Material.LAVA);
}
