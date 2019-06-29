package com.hyper1423.somestuff.blocks.machines.blastfurnace.slots;

import com.hyper1423.somestuff.blocks.machines.blastfurnace.TileEntityBlastFurnace;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBlastFurnaceFuel extends SlotItemHandler {

	public SlotBlastFurnaceFuel(IItemHandler inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		// TODO Auto-generated method stub
		return TileEntityBlastFurnace.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		// TODO Auto-generated method stub
		return super.getItemStackLimit(stack);
	}
}
