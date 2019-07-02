package com.hyper1423.somestuff.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBlastFurnaceOutput extends SlotItemHandler {

	private final EntityPlayer player;
	private int removeCount;
	
	public SlotBlastFurnaceOutput(EntityPlayer player, IItemHandler inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.player = player;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		// TODO Auto-generated method stub
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
		// TODO Auto-generated method stub
		if(this.getHasStack()) this.removeCount += Math.min(amount, this.getStack().getCount());
		return super.decrStackSize(amount);
	}

}
