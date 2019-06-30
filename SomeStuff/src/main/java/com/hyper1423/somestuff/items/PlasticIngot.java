package com.hyper1423.somestuff.items;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class PlasticIngot extends Item {

	public PlasticIngot(String name) {
		this(name, Main.someStuff);
	}
	
	public PlasticIngot(String name, CreativeTabs creativeTab) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(creativeTab);
		
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		super.getSubItems(tab, items);
	}
}
