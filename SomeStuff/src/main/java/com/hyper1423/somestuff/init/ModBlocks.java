package com.hyper1423.somestuff.init;

import java.util.ArrayList;
import java.util.List;

import com.hyper1423.somestuff.blocks.CopperBlock;
import com.hyper1423.somestuff.blocks.CopperOre;
import com.hyper1423.somestuff.blocks.PlasticBlock;
import com.hyper1423.somestuff.blocks.machines.blastfurnace.BlastFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
			
			public static final Block PLASTIC_BLOCK = new PlasticBlock("plastic_block", Material.IRON);
			public static final Block COPPER_ORE = new CopperOre("copper_ore", Material.ROCK);
			public static final Block COPPER_BLOCK = new CopperBlock("copper_block", Material.IRON);
			public static final Block BLAST_FURNACE = new BlastFurnace("blast_furnace", Material.LAVA);
}
