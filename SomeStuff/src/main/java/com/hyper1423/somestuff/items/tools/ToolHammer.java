package com.hyper1423.somestuff.items.tools;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.items.tools.classes.ItemHammer;
import com.hyper1423.somestuff.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;

public class ToolHammer extends ItemHammer implements IHasModel {
	public ToolHammer(String name, ToolMaterial material) {
		super(material);
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
