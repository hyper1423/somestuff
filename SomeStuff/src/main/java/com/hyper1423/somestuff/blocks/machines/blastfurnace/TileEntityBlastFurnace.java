package com.hyper1423.somestuff.blocks.machines.blastfurnace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hyper1423.somestuff.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
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
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBlastFurnace extends TileEntity implements ITickable {

	private ItemStackHandler inventory = new ItemStackHandler(NonNullList.withSize(4, ItemStack.EMPTY));
	private String customName;
	private ItemStack smelting = ItemStack.EMPTY;
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String BURNTIME_KEY = "BurnTime";
	private static final String COOKTIME_KEY = "CookTime";
	private static final String COOKTIMETOTAL_KEY = "CookTimeTotal";

	private static final String INVENTORY_KEY = "inventory";
	private static final String CUSTOMNAME_KEY = "CustomName";

	public int burnTime = 0;
	public int currentBurnTime = 0;
	public int cookTime = 0;
	public int totalCookTime = 600;
	
	public TileEntityBlastFurnace() {
		LOGGER.info("TileEntity is successfully bound to BlastFurnace block");
	}
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.inventory;
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
				: new TextComponentTranslation("");
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag(INVENTORY_KEY));
		burnTime = compound.getInteger(BURNTIME_KEY);
		cookTime = compound.getInteger(COOKTIME_KEY);
		totalCookTime = compound.getInteger(COOKTIMETOTAL_KEY);
		currentBurnTime = getItemBurnTime((ItemStack) inventory.getStackInSlot(1));

		if (compound.hasKey(CUSTOMNAME_KEY, 8))
			setCustomName(compound.getString(CUSTOMNAME_KEY));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);
		compound.setInteger(BURNTIME_KEY, (short) burnTime);
		compound.setInteger(COOKTIME_KEY, (short) cookTime);
		compound.setInteger(COOKTIMETOTAL_KEY, (short) totalCookTime);
		compound.setTag(INVENTORY_KEY, this.inventory.serializeNBT());

		if (hasCustomName())
			compound.setString(CUSTOMNAME_KEY, customName);
		return compound;
	}

	public boolean isBurning() {
		return burnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityBlastFurnace te) {
		return te.burnTime > 0;
	}

	@Override
	public void update() {

		if (isBurning()) {
			--burnTime;
			BlastFurnace.setState(true, world, pos);
		}
		ItemStack fuel = (ItemStack) inventory.getStackInSlot(1);

		if (isBurning() || !fuel.isEmpty() && !inventory.getStackInSlot(0).isEmpty()) {
			if (!isBurning() && canSmelt()) {
				burnTime = getItemBurnTime(fuel);
				currentBurnTime = burnTime;

				if (isBurning() && !fuel.isEmpty()) {
					Item item = fuel.getItem();
					fuel.shrink(1);

					if (fuel.isEmpty()) {
						ItemStack item1 = item.getContainerItem(fuel);
						inventory.setStackInSlot(1, item1);
					}
				}
			}

			if (isBurning() && canSmelt() && cookTime > 0) {
				cookTime++;

				if (cookTime == totalCookTime) {
					if (inventory.getStackInSlot(2).getCount() > 0) {
						inventory.getStackInSlot(2).grow(1);
					} else {
						inventory.insertItem(2, smelting, false);
					}

					if (inventory.getStackInSlot(3).getCount() > 0) {
						inventory.getStackInSlot(3).grow(1);
					} else {
						inventory.insertItem(3, new ItemStack(ModItems.SLAG, 1), false);
					}
					smelting = ItemStack.EMPTY;
					cookTime = 0;
				}
				return;
			}

//			} else if (!isBurning() && cookTime > 0) {
//				if (cookTime > getCookTime(inventory.getStackInSlot(1)) / 3)
//					cookTime = MathHelper.clamp(cookTime - 3, 0, totalCookTime);
//				else
//					MathHelper.clamp(cookTime - 2, 0, totalCookTime);
			else {
				if (this.canSmelt() && this.isBurning()) {
					ItemStack output = BlastFurnaceRecipes.getInstance().getResult(inventory.getStackInSlot(0));
					if (!output.isEmpty()) {
						smelting = output;
						cookTime++;
						inventory.getStackInSlot(0).shrink(1);
					}
				}
			}
		}
//			if (flag != isBurning()) {
//				flag1 = true;
//				BlastFurnace.setState(isBurning(), world, pos);
//			}
//		}
//		if (flag1) {
//			markDirty();
//		}
	}

	private boolean canSmelt() {
		if (((ItemStack) inventory.getStackInSlot(0)).isEmpty())
			return false;
		else {
			ItemStack result = BlastFurnaceRecipes.getInstance().getResult((ItemStack) inventory.getStackInSlot(0));
			if (result.isEmpty())
				return false;
			else {
				ItemStack output = (ItemStack) inventory.getStackInSlot(2);
				ItemStack slug = (ItemStack) inventory.getStackInSlot(3);
				if (output.isEmpty())
					return true;
				if (!output.isItemEqual(result))
					return false;
				int res1 = output.getCount() + result.getCount();
				int res2 = slug.getCount() + 1;
				return (res1 <= 64 && res1 <= output.getMaxStackSize())
						|| (res2 <= 64 && res2 <= slug.getMaxStackSize());
			}
		}
	}

	public static int getItemBurnTime(ItemStack fuel) {
		if (fuel.isEmpty())
			return 0;
		else {
			Item item = fuel.getItem();

			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);

				if (block == Blocks.WOODEN_SLAB)
					return 100;
				if (block == Blocks.ACACIA_STAIRS || block == Blocks.OAK_STAIRS || block == Blocks.JUNGLE_STAIRS
						|| block == Blocks.BIRCH_STAIRS || block == Blocks.DARK_OAK_STAIRS
						|| block == Blocks.SPRUCE_STAIRS)
					return 150;
				if (block == Blocks.ACACIA_FENCE || block == Blocks.OAK_FENCE || block == Blocks.JUNGLE_FENCE
						|| block == Blocks.BIRCH_FENCE || block == Blocks.DARK_OAK_FENCE
						|| block == Blocks.SPRUCE_FENCE
						
						||
						
					block == Blocks.ACACIA_FENCE_GATE || block == Blocks.OAK_FENCE_GATE || block == Blocks.JUNGLE_FENCE_GATE
						|| block == Blocks.BIRCH_FENCE_GATE || block == Blocks.DARK_OAK_FENCE_GATE
						|| block == Blocks.SPRUCE_FENCE_GATE)
					return 100;
				if (block.getDefaultState().getMaterial() == Material.WOOD)
					return 200;
				if (block == Blocks.COAL_BLOCK)
					return 14400;
			}

			if (item instanceof ItemTool && "WOOD".contentEquals(((ItemTool) item).getToolMaterialName()))
				return 180;
			if (item instanceof ItemSword && "WOOD".contentEquals(((ItemTool) item).getToolMaterialName()))
				return 180;
			if (item instanceof ItemHoe && "WOOD".contentEquals(((ItemTool) item).getToolMaterialName()))
				return 180;
			if (item == Items.STICK)
				return 50;
			if (item == Items.COAL)
				return 1600;
			if (item == Items.LAVA_BUCKET)
				return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING))
				return 70;
			if (item == Items.BLAZE_ROD)
				return 1600;

			return ForgeEventFactory.getItemBurnTime(fuel);
		}
	}

	public static boolean isItemFuel(ItemStack fuel) {
		return getItemBurnTime(fuel) > 0;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		// TODO Auto-generated method stub
		return (!oldState.getBlock().equals(newState.getBlock())) || (!oldState.equals(newState));
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {

		return world.getTileEntity(pos) != this ? false
				: player.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D,
						(double) pos.getZ() + 0.5D) <= 64.0D;
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 2 || index == 3)
			return false;
		if (index != 1)
			return true;
		else {
			return isItemFuel(stack);
		}
	}
}
