package com.hyper1423.somestuff.block;

import java.util.Random;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.block.materials.ModMaterials;
import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
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
//		entityIn.fallDistance *= 0.5F;
//		entityIn.motionX /= 5;
//		entityIn.motionZ /= 5;
//		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
	}

	@Override
	public boolean isPassable(IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		double x = pos.getX() + 0.5d;
		double y = pos.getY() + 0.5d;
		double z = pos.getZ() + 0.5d;
		if (worldIn.isRainingAt(pos)) {
			float randoffset = rand.nextFloat() - 0.5f;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y + randoffset, z, 0.0D, 0.5D, 0.0D);
		}
		worldIn.playSound(x, y, z, SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
	}
}
