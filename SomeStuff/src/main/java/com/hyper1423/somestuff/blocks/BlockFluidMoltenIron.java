package com.hyper1423.somestuff.blocks;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidMoltenIron extends BlockFluidFinite {
	public BlockFluidMoltenIron(Fluid fluid) {
		this(fluid, Main.someStuff);
	}
	public BlockFluidMoltenIron(Fluid fluid, CreativeTabs ct) {
		super(fluid, Material.LAVA);
		setUnlocalizedName(fluid.getName());
		setRegistryName(fluid.getName());
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

}
