package com.hyper1423.somestuff.init;

import java.util.ArrayList;
import java.util.List;

import com.hyper1423.somestuff.block.BlockAlloyingMachine;
import com.hyper1423.somestuff.block.BlockBlastFurnace;
import com.hyper1423.somestuff.block.BlockCopper;
import com.hyper1423.somestuff.block.BlockCopperOre;
import com.hyper1423.somestuff.block.BlockFluidMoltenIron;
import com.hyper1423.somestuff.block.BlockPlastic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

public class ModBlocks{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
			
			public static final Block PLASTIC_BLOCK = new BlockPlastic("plastic_block", Material.GLASS);
			public static final Block COPPER_ORE = new BlockCopperOre("copper_ore", Material.ROCK);
			public static final Block COPPER_BLOCK = new BlockCopper("copper_block", Material.IRON);
			
			public static final Block BLAST_FURNACE = new BlockBlastFurnace("blast_furnace", Material.IRON);
			public static final Block ALLOYING_MACHINE = new BlockAlloyingMachine("alloying_machine", Material.IRON);
			
			public static final BlockFluidClassic BLOCK_MOLTEN_IRON = new BlockFluidMoltenIron(ModFluids.MOLTEN_IRON);
}