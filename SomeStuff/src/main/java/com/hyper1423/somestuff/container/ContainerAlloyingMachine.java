package com.hyper1423.somestuff.container;

import com.hyper1423.somestuff.container.slot.SlotBlastFurnaceOutput;
import com.hyper1423.somestuff.recipes.BlastFurnaceRecipes;
import com.hyper1423.somestuff.tileentities.TileEntityAlloyingMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAlloyingMachine extends Container {

	private final TileEntityAlloyingMachine tileentity;
	private int meltingTimeLeft, meltingTimeRight, totalMeltingTime, alloyingTimeLeft, alloyingTimeRight, totalAlloyingTime;

	public ContainerAlloyingMachine(InventoryPlayer player, TileEntityAlloyingMachine tileentity) {
		this.tileentity = tileentity;
		if (this.tileentity != null) {
			IItemHandler inventory = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			this.addSlotToContainer(new SlotItemHandler(inventory, 0, 35, 17));
			this.addSlotToContainer(new SlotBlastFurnaceOutput(player.player, inventory, 1, 143, 53));

			for (int y = 0; y < 3; y++) {
				for (int x = 0; x < 9; x++) {
					this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
				}
			}

			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
			}
		}
	}

	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener) this.listeners.get(i);
			if (this.meltingTimeLeft != this.tileentity.meltingTimeLeft)
				listener.sendWindowProperty(this, 2, this.tileentity.meltingTimeLeft);
			if (this.alloyingTimeLeft != this.tileentity.alloyingTimeLeft)
				listener.sendWindowProperty(this, 2, this.tileentity.alloyingTimeLeft);
			if (this.totalMeltingTime != this.tileentity.totalMeltingTime)
				listener.sendWindowProperty(this, 3, this.tileentity.totalMeltingTime);
			if (this.totalAlloyingTime != this.tileentity.totalAlloyingTime)
				listener.sendWindowProperty(this, 3, this.tileentity.totalAlloyingTime);
			if (this.meltingTimeLeft != this.tileentity.meltingTimeRight)
				listener.sendWindowProperty(this, 2, this.tileentity.meltingTimeRight);
			if (this.alloyingTimeLeft != this.tileentity.alloyingTimeRight)
				listener.sendWindowProperty(this, 2, this.tileentity.alloyingTimeRight);
			if (this.totalMeltingTime != this.tileentity.totalMeltingTime)
				listener.sendWindowProperty(this, 3, this.tileentity.totalMeltingTime);
			if (this.totalAlloyingTime != this.tileentity.totalAlloyingTime)
				listener.sendWindowProperty(this, 3, this.tileentity.totalAlloyingTime);
		}
		this.meltingTimeLeft = this.tileentity.meltingTimeLeft;
		this.alloyingTimeLeft = this.tileentity.alloyingTimeLeft;
		this.meltingTimeRight = this.tileentity.meltingTimeRight;
		this.alloyingTimeRight = this.tileentity.alloyingTimeRight;
		this.totalMeltingTime = this.tileentity.totalMeltingTime;
		this.totalAlloyingTime = this.tileentity.totalAlloyingTime;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return this.tileentity.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (index == 1) {
				if (!this.mergeItemStack(stack1, 2, 38, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			} else if (index == 0) {
				if (!this.mergeItemStack(stack1, 0, 1, false)) {
					return ItemStack.EMPTY;
				} else if (index >= 2 && index < 29) {
					if (!this.mergeItemStack(stack1, 29, 38, false))
						return ItemStack.EMPTY;
				} else if (index >= 29 && index < 38 && !this.mergeItemStack(stack1, 2, 29, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 2, 38, false)) {
				return ItemStack.EMPTY;
			}
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();

			}
			if (stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}

}
