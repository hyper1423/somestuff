package com.hyper1423.somestuff.blocks;

import java.util.Random;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.init.ModMaterials;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluidMoltenIron extends BlockFluidClassic {
	public BlockFluidMoltenIron(Fluid fluid) {
		this(fluid, Main.someStuff);
	}

	public BlockFluidMoltenIron(Fluid fluid, CreativeTabs ct) {
		super(fluid, ModMaterials.MOLTEN_IRON);
		setUnlocalizedName(fluid.getName());
		setRegistryName(fluid.getName());
		setDensity(6980);
		setTemperature(1811);
		setLightLevel(1.0f);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		// TODO Auto-generated method stub
		if (!entityIn.isImmuneToFire()) {
			entityIn.attackEntityFrom(DamageSource.LAVA, 4.0F);
			entityIn.setFire(15);
		}
		entityIn.fallDistance *= 0.5F;
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		entityIn.motionX /= 4;
		entityIn.motionZ /= 4;
		entityIn.motionY /= 5;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		// TODO Auto-generated method stub
		if (worldIn.isRainingAt(pos)) {
			double x = pos.getX() + 0.5d;
			double y = pos.getY() + 0.5d;
			double z = pos.getZ() + 0.5d;
			float randoffset = rand.nextFloat() - 0.5f;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y + randoffset, z, 0.0D, 0.0D, 0.0D);
		}
	}
}
