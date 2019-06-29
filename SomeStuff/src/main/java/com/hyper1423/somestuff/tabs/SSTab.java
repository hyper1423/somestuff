package com.hyper1423.somestuff.tabs;

import com.hyper1423.somestuff.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SSTab extends CreativeTabs {

	public SSTab(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ItemStack getTabIconItem() {return new ItemStack(ModItems.COPPER_HAMMER);}

}
