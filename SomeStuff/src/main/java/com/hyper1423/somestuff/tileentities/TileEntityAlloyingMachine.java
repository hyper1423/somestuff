package com.hyper1423.somestuff.tileentities;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hyper1423.somestuff.recipes.AlloyingMachineRecipes;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityAlloyingMachine extends TileEntity implements ITickable {

	private ItemStackHandler inventory = new ItemStackHandler(NonNullList.withSize(4, ItemStack.EMPTY));
	private NonNullList<FluidTank> fluidInventory = NonNullList.<FluidTank>withSize(3, new FluidTank(2000));
	private IEnergyStorage energyStorage = new EnergyStorage(10000);

	private String customName = "";
	private ItemStack smelting = ItemStack.EMPTY;

	private static final String MELTINGTIMELEFT_KEY = "MeltingTime";
	private static final String ALLOYINGTIMELEFT_KEY = "AlloyingTime";
	private static final String MELTINGTIMERIGHT_KEY = "MeltingTime";
	private static final String ALLOYINGTIMERIGHT_KEY = "AlloyingTime";
	private static final String TOTALMELTINGTIME_KEY = "TotalMeltingTime";
	private static final String TOTALALLOYINGTIME_KEY = "TotalAlloyingTime";

	private static final String INVENTORY_KEY = "inventory";
	private static final String FLUIDINVENTORY_KEY = "fluid";
	private static final String CUSTOMNAME_KEY = "CustomName";
	private static final String ENERGY_KEY = "Energy";

	private static final String ENERGY_CAPACITY_KEY = "capacity";
	private static final String ENERGY_LEFT_KEY = "energyleft";

	public int meltingTimeLeft = 0;
	public int alloyingTimeLeft = 0;
	public int meltingTimeRight = 0;
	public int alloyingTimeRight = 0;
	public int totalMeltingTime = 300;
	public int totalAlloyingTime = 200;

	public TileEntityAlloyingMachine() {
		Reference.LOGGER.info("TileEntity is successfully bound to a block");
		for (FluidTank tank : fluidInventory) {
			tank.setTileEntity(this);
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				|| capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				|| capability == CapabilityEnergy.ENERGY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.inventory;
		else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.fluidInventory;
		else if (capability == CapabilityEnergy.ENERGY)
			return (T) this.energyStorage;
		return super.getCapability(capability, facing);
	}

	public boolean hasCustomName() {

		return customName != null && !customName.isEmpty();
	}

	public void setCustomName(String customname) {
		this.customName = customname;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName)
				: new TextComponentTranslation("Alloying Machine");
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		NBTTagCompound energyCompound = compound.getCompoundTag(ENERGY_KEY);

		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag(INVENTORY_KEY));
		energyStorage = new EnergyStorage(energyCompound.getInteger(ENERGY_CAPACITY_KEY),
				energyCompound.getInteger(ENERGY_CAPACITY_KEY), energyCompound.getInteger(ENERGY_CAPACITY_KEY),
				energyCompound.getInteger(ENERGY_LEFT_KEY));
		meltingTimeLeft = compound.getInteger(MELTINGTIMELEFT_KEY);
		alloyingTimeLeft = compound.getInteger(ALLOYINGTIMELEFT_KEY);
		meltingTimeRight = compound.getInteger(MELTINGTIMERIGHT_KEY);
		alloyingTimeRight = compound.getInteger(ALLOYINGTIMERIGHT_KEY);
		totalMeltingTime = compound.getInteger(TOTALMELTINGTIME_KEY);
		totalAlloyingTime = compound.getInteger(TOTALALLOYINGTIME_KEY);
		customName = compound.getString(CUSTOMNAME_KEY);

		for (int index = 0; index < fluidInventory.size(); index++) {
			fluidInventory.set(index, fluidInventory.get(index).readFromNBT(
					(NBTTagCompound) ((NBTTagCompound) compound.getTag(FLUIDINVENTORY_KEY)).getTag("Tank" + index)));
		}

		if (compound.hasKey(CUSTOMNAME_KEY, 8))
			setCustomName(compound.getString(CUSTOMNAME_KEY));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		NBTTagCompound energyCompound = new NBTTagCompound();

		super.writeToNBT(compound);
		compound.setTag(INVENTORY_KEY, this.inventory.serializeNBT());
		energyCompound.setInteger(ENERGY_CAPACITY_KEY, energyStorage.getMaxEnergyStored());
		energyCompound.setInteger(ENERGY_LEFT_KEY, energyStorage.getEnergyStored());
		compound.setTag(ENERGY_KEY, energyCompound);
		compound.setInteger(MELTINGTIMELEFT_KEY, meltingTimeLeft);
		compound.setInteger(ALLOYINGTIMELEFT_KEY, alloyingTimeLeft);
		compound.setInteger(MELTINGTIMERIGHT_KEY, meltingTimeRight);
		compound.setInteger(ALLOYINGTIMERIGHT_KEY, alloyingTimeRight);
		compound.setInteger(TOTALMELTINGTIME_KEY, totalMeltingTime);
		compound.setInteger(TOTALALLOYINGTIME_KEY, totalAlloyingTime);

		for (int index = 0; index < fluidInventory.size(); index++) {
			NBTTagCompound fluidInventoryCompound = new NBTTagCompound();
			fluidInventoryCompound.setTag("Tank" + index, fluidInventory.get(index).writeToNBT(fluidInventoryCompound));
			compound.setTag(FLUIDINVENTORY_KEY, fluidInventoryCompound);
		}

		if (hasCustomName())
			compound.setString(CUSTOMNAME_KEY, customName);
		return compound;
	}

	public boolean isMelting(EnumDirection index) throws IllegalDirectionException {
		switch (index) {
		case LEFT:
			return meltingTimeLeft > 0;
		case RIGHT:
			return meltingTimeRight > 0;
		default:
			throw new TileEntityAlloyingMachine.IllegalDirectionException("You can only put EnumDirection.LEFT or EnumDirection.RIGHT in index.");
		}
	}

	@SideOnly(Side.CLIENT)
	public static boolean isMelting(TileEntityAlloyingMachine te, EnumDirection index) throws IllegalDirectionException {
		switch (index) {
		case LEFT:
			return te.meltingTimeLeft > 0;
		case RIGHT:
			return te.meltingTimeRight > 0;
		default:
			throw new TileEntityAlloyingMachine.IllegalDirectionException("You can only put EnumDirection.LEFT or EnumDirection.RIGHT in index.");
		}
	}

	public boolean isAlloying(EnumDirection index) throws IllegalDirectionException {
		switch (index) {
		case LEFT:
			return meltingTimeLeft > 0;
		case RIGHT:
			return meltingTimeRight > 0;
		default:
			throw new TileEntityAlloyingMachine.IllegalDirectionException("You can only put EnumDirection.LEFT or EnumDirection.RIGHT in index.");
		}
	}

	@SideOnly(Side.CLIENT)
	public static boolean isAlloying(TileEntityAlloyingMachine te, EnumDirection index) throws IllegalDirectionException {
		switch (index) {
		case LEFT:
			return te.meltingTimeLeft > 0;
		case RIGHT:
			return te.meltingTimeRight > 0;
		default:
			throw new TileEntityAlloyingMachine.IllegalDirectionException("You can only put EnumDirection.LEFT or EnumDirection.RIGHT in index.");
		}
	}

	@Override
	public void update() {

	}

	private boolean canAlloy() {
		FluidStack result = AlloyingMachineRecipes.getInstance().getAlloyingResult(fluidInventory.get(0).getFluid(),
				fluidInventory.get(1).getFluid());
		if (result == null)
			return false;
		else {
			FluidStack output = fluidInventory.get(2).getFluid();
			if (output == null)
				return true;
			if (!output.isFluidEqual(result))
				return false;
			int count = output.amount + result.amount;
			return count <= fluidInventory.get(2).getCapacity();
		}
	}

	private boolean canMelt(int index) {
		FluidStack result = AlloyingMachineRecipes.getInstance().getMeltingResult(inventory.getStackInSlot(index));
		if (result == null)
			return false;
		else {
			FluidStack output = fluidInventory.get(index).getFluid();
			if (output == null)
				return true;
			if (!output.isFluidEqual(result))
				return false;
			int count = output.amount + result.amount;
			return count <= fluidInventory.get(2).getCapacity();
		}
	}

	public static int getItemEnergy(ItemStack fuel) {
		if (fuel.getItem().equals(Items.REDSTONE))
			return 500;
		return 0;
	}

	public static boolean isItemFuel(ItemStack fuel) {
		return getItemEnergy(fuel) > 0;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return (!oldState.getBlock().equals(newState.getBlock())) || (!oldState.equals(newState));
	}

	public boolean isUsableByPlayer(EntityPlayer player) {

		return world.getTileEntity(pos) != this ? false
				: player.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D,
						(double) pos.getZ() + 0.5D) <= 64.0D;
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 0)
			return true;
		else
			return false;
	}

	public final ArrayList<ItemStack> containerLists() {
		ArrayList<ItemStack> stackList = new ArrayList(inventory.getSlots());
		for (int i = 0; i < inventory.getSlots(); i++) {
			stackList.add(inventory.getStackInSlot(i));
		}
		return stackList;
	}

	public enum EnumDirection {
		UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");

		private final String toString;

		private EnumDirection(String toString) {
			this.toString = toString;
		}

		@Override
		public String toString() {
			return toString;
		}
	}
	
	public static class IllegalDirectionException extends IllegalArgumentException {
		public IllegalDirectionException(String msg) {
			super(msg);
		}
	}
}
