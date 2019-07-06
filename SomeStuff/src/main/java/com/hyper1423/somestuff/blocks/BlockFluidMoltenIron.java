package com.hyper1423.somestuff.blocks;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.init.ModMaterials;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidMoltenIron extends BlockFluidClassic {
	public BlockFluidMoltenIron(Fluid fluid) {
		this(fluid, Main.someStuff);
	}
	
	public BlockFluidMoltenIron(Fluid fluid, CreativeTabs ct) {
		super(fluid, ModMaterials.MOLTEN_IRON);
		setUnlocalizedName(fluid.getName());
		setRegistryName(fluid.getName());
		setDensity(6980);
		setTemperature(1811);
		setLightLevel(1.0f);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
