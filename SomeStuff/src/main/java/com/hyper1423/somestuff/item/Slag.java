package com.hyper1423.somestuff.item;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Slag extends Item {

	public Slag(String name) {
		this(name, Main.someStuff);
	}
	
	public Slag(String name, CreativeTabs creativeTab) {
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