package com.hyper1423.somestuff.block;

import java.util.Random;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.tileentities.TileEntityAlloyingMachine;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockAlloyingMachine extends Block {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

	public BlockAlloyingMachine(String name, Material material) {
		this(name, material, Main.someStuff);
	}

	public BlockAlloyingMachine(String name, Material material, CreativeTabs creativeTab) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(creativeTab);

		setSoundType(SoundType.ANVIL);
		setHardness(5.0f);
		setResistance(5.0f);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(0.0f);
		// setLightOpacity(1);
		// setBlockUnbreakable();

		this.setDefaultState(
				this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVATED, false));

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.ALLOYING_MACHINE);

	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ModBlocks.ALLOYING_MACHINE);

	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//		if (playerIn.hasItemInSlot(EntityEquipmentSlot.MAINHAND)) {
//			Reference.LOGGER.info("line 93 activated");
//			ItemStack bucket = playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
//			if (bucket.getItem() instanceof UniversalBucket) {
//				Reference.LOGGER.info("line 96 activated: item holding is instance of UniversalBucket");
//				if (((UniversalBucket) bucket.getItem()).getFluid(bucket) != null) {
//					if (worldIn.getTileEntity(pos) instanceof TileEntityAlloyingMachine) {
//						Reference.LOGGER.info("line 98 activated");
//						FluidTank tank0 = (FluidTank) ((TileEntityAlloyingMachine) worldIn.getTileEntity(pos))
//								.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.WEST);
//						
//						FluidTank tank1 = (FluidTank) ((TileEntityAlloyingMachine) worldIn.getTileEntity(pos))
//								.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.EAST);
//						
//						FluidTank tank2 = (FluidTank) ((TileEntityAlloyingMachine) worldIn.getTileEntity(pos))
//								.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
//						
//						if (tank0.getFluidAmount() + Fluid.BUCKET_VOLUME <= tank0.getCapacity())
//							
//							((TileEntityAlloyingMachine) worldIn.getTileEntity(pos))
//									.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.WEST)
//									.fill(new FluidStack(((UniversalBucket) bucket.getItem()).getFluid(bucket),
//											Fluid.BUCKET_VOLUME), true);
//					}
//				}
//			}
//		}
		if (playerIn.getHeldItemMainhand().getItem() instanceof UniversalBucket || playerIn.getHeldItemMainhand().getItem() instanceof ItemBucket) {
			if (!FluidUtil.interactWithFluidHandler(playerIn, hand,
					((TileEntityAlloyingMachine) worldIn.getTileEntity(pos))
							.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.WEST))) {

				if (!FluidUtil.interactWithFluidHandler(playerIn, hand,
						((TileEntityAlloyingMachine) worldIn.getTileEntity(pos))
								.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.EAST)))

					FluidUtil.tryFillContainerAndStow(playerIn.getHeldItemMainhand(),
							((TileEntityAlloyingMachine) worldIn.getTileEntity(pos))
									.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP),
							playerIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null),
							Fluid.BUCKET_VOLUME, playerIn, true);
			}
		} else if (!worldIn.isRemote) {
			playerIn.openGui(Main.instance, Reference.GUI_ALLOYING_MACHINE, worldIn, pos.getX(), pos.getY(),
					pos.getZ());
		}
		return true;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub

		if (!worldIn.isRemote) {

			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = (EnumFacing) state.getValue(FACING);

			if (face == EnumFacing.NORTH)
				face = EnumFacing.SOUTH;
			if (face == EnumFacing.SOUTH)
				face = EnumFacing.NORTH;
			if (face == EnumFacing.WEST)
				face = EnumFacing.EAST;
			if (face == EnumFacing.EAST)
				face = EnumFacing.WEST;
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (active)
			worldIn.setBlockState(pos, ModBlocks.ALLOYING_MACHINE.getDefaultState()
					.withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVATED, true), 1 | 2);
		else
			worldIn.setBlockState(pos, ModBlocks.ALLOYING_MACHINE.getDefaultState()
					.withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVATED, false), 1 | 2);

		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		// TODO Auto-generated method stub
		return state.getValue(ACTIVATED).booleanValue() ? 15 : 0;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		// TODO Auto-generated method stub
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		// TODO Auto-generated method stub
		worldIn.setBlockState(pos,
				this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		// TODO Auto-generated method stub
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		// TODO Auto-generated method stub
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		// TODO Auto-generated method stub
		return new BlockStateContainer(this, new IProperty[] { ACTIVATED, FACING });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		EnumFacing facing = EnumFacing.getFront(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y)
			facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TileEntityAlloyingMachine createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return new TileEntityAlloyingMachine();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileEntityAlloyingMachine) {
			for (ItemStack stack : ((TileEntityAlloyingMachine) te).containerLists()) {
				spawnAsEntity(worldIn, pos, stack);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}

	/*
	 * @Override
	 * 
	 * @SuppressWarnings("incomplete-switch")
	 * 
	 * @SideOnly(Side.CLIENT) public void randomDisplayTick(IBlockState stateIn,
	 * World worldIn, BlockPos pos, Random rand) { // TODO Auto-generated method
	 * stub if (stateIn.getValue(ACTIVATED)) { EnumFacing facing =
	 * stateIn.getValue(FACING); double x = pos.getX() + 0.5d; double y = pos.getY()
	 * + 0.5d; double z = pos.getZ() + 0.5d; double offset = 0.52d; double
	 * randoffset = rand.nextDouble() * 0.6d - 0.3d; double smallrandoffset =
	 * rand.nextDouble() * 0.4d - 0.2d; if (rand.nextDouble() < 0.1D) {
	 * worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double)
	 * pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE,
	 * SoundCategory.BLOCKS, 1.0F, 1.0F, false); } switch (facing) { case NORTH:
	 * worldIn.spawnParticle(EnumParticleTypes.FLAME, x + randoffset, y, z - offset,
	 * 0.0D, 0.0D, 0.0D); break; case SOUTH:
	 * worldIn.spawnParticle(EnumParticleTypes.FLAME, x + randoffset, y, z + offset,
	 * 0.0D, 0.0D, 0.0D); break; case WEST:
	 * worldIn.spawnParticle(EnumParticleTypes.FLAME, x - offset, y, z + randoffset,
	 * 0.0D, 0.0D, 0.0D); break; case EAST:
	 * worldIn.spawnParticle(EnumParticleTypes.FLAME, x + offset, y, z + randoffset,
	 * 0.0D, 0.0D, 0.0D); break; }
	 * worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x + smallrandoffset, y,
	 * z + smallrandoffset, 0.0D, 0.0D, 0.0D); } }
	 */
}
