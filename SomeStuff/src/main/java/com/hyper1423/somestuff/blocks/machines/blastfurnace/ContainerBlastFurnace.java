package com.hyper1423.somestuff.blocks.machines.blastfurnace;

import com.hyper1423.somestuff.blocks.machines.blastfurnace.slots.SlotBlastFurnaceFuel;
import com.hyper1423.somestuff.blocks.machines.blastfurnace.slots.SlotBlastFurnaceOutput;

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
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBlastFurnace extends Container {

	private final TileEntityBlastFurnace tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;

	public ContainerBlastFurnace(InventoryPlayer player, TileEntityBlastFurnace tileentity) {
		this.tileentity = tileentity;
		if (this.tileentity == null)
			System.out.println("Ouch. I hate this error.|ERR: line 25");
		if (tileentity == null)
			System.out.println("Ouch... probably WORST error in the world: NULLPOINTEREXCEPTION.|ERR: line 26");
		IItemHandler inventory = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		System.out.println(
				this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).equals(inventory));

		this.addSlotToContainer(new SlotItemHandler(inventory, 0, 35, 17));
		this.addSlotToContainer(new SlotBlastFurnaceFuel(inventory, 1, 35, 53));
		this.addSlotToContainer(new SlotBlastFurnaceOutput(player.player, inventory, 2, 95, 35));
		this.addSlotToContainer(new SlotBlastFurnaceOutput(player.player, inventory, 3, 143, 53));

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}

	@Override
	public void detectAndSendChanges() {
		// TODO Auto-generated method stub
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener) this.listeners.get(i);

			if (this.burnTime != this.tileentity.burnTime)
				listener.sendWindowProperty(this, 0, this.tileentity.burnTime);
			if (this.currentBurnTime != this.tileentity.currentBurnTime)
				listener.sendWindowProperty(this, 1, this.tileentity.currentBurnTime);
			if (this.cookTime != this.tileentity.cookTime)
				listener.sendWindowProperty(this, 2, this.tileentity.cookTime);
			if (this.totalCookTime != this.tileentity.totalCookTime)
				listener.sendWindowProperty(this, 3, this.tileentity.totalCookTime);
		}

		this.burnTime = this.tileentity.burnTime;
		this.currentBurnTime = this.tileentity.currentBurnTime;
		this.cookTime = this.tileentity.cookTime;
		this.totalCookTime = this.tileentity.totalCookTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		// TODO Auto-generated method stub
		switch (id) {
		case 0:
			this.tileentity.burnTime = data;
			break;
		case 1:
			this.tileentity.currentBurnTime = data;
			break;
		case 2:
			this.tileentity.cookTime = data;
			break;
		case 3:
			this.tileentity.totalCookTime = data;
			break;
		}
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

			if (index == 2 || index == 3) {
				if (!this.mergeItemStack(stack1, 4, 40, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			} else if (index != 1 && index != 0) {
				if (!BlastFurnaceRecipes.getInstance().getResult(stack1).isEmpty()) {
					if (!this.mergeItemStack(stack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					} else if (TileEntityBlastFurnace.isItemFuel(stack1)) {
						if (!this.mergeItemStack(stack1, 1, 2, false))
							return ItemStack.EMPTY;
					} else if (TileEntityBlastFurnace.isItemFuel(stack1)) {
						if (!this.mergeItemStack(stack1, 1, 2, false))
							return ItemStack.EMPTY;
					} else if (TileEntityBlastFurnace.isItemFuel(stack1)) {
						if (!this.mergeItemStack(stack1, 1, 2, false))
							return ItemStack.EMPTY;
					} else if (index >= 4 && index < 31) {
						if (!this.mergeItemStack(stack1, 31, 40, false))
							return ItemStack.EMPTY;
					} else if (index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.mergeItemStack(stack1, 4, 40, false)) {
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
