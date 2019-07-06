package com.hyper1423.somestuff.proxy;

import net.minecraft.item.Item;

public interface CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id);
}
