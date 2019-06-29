package com.hyper1423.somestuff.blocks;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class PlasticBlock extends Block implements IHasModel {
	public PlasticBlock(String name, Material material) {
		this(name, material, Main.someStuff);
	}

	public PlasticBlock(String name, Material material, CreativeTabs creativeTab) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(creativeTab);

		setSoundType(SoundType.GLASS);
		setHardness(1.5f);
		setResistance(1.5f);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0f);
		// setLightOpacity(1);
		// setBlockUnbreakable();

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
