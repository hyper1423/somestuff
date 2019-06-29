package com.hyper1423.somestuff.items;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * TODO: Removing this class
 * @author hyper1423
 * @deprecated
 * @since 0.0.1
 */
public class ItemBase extends Item implements IHasModel {

	/**
	 * TODO: Removing this class
	 * @author hyper1423
	 * @deprecated
	 * @since 0.0.1
	 */
	public ItemBase(String name) {
		this(name, CreativeTabs.MATERIALS);
	}
	
	/**
	 * TODO: Removing this class
	 * @author hyper1423
	 * @deprecated
	 * @since 0.0.1
	 */
	public ItemBase(String name, CreativeTabs creativeTab) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(creativeTab);
		
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		super.getSubItems(tab, items);
	}
}
