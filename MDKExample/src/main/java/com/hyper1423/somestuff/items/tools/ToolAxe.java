package com.hyper1423.somestuff.items.tools;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe implements IHasModel {
	public ToolAxe(String name, ToolMaterial material) {

		super(material, 10.0F, -3.2F);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);

		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
