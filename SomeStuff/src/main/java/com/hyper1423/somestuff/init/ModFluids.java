package com.hyper1423.somestuff.init;

import java.util.ArrayList;
import java.util.List;

import com.hyper1423.somestuff.block.BlockFluidMoltenIron;
import com.hyper1423.somestuff.fluid.FluidMoltenIron;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {
	public static final List<Fluid> FLUIDS = new ArrayList<Fluid>();
	public static final List<Fluid> BUCKETLIST = new ArrayList<Fluid>();
	public static final List<BlockFluidBase> BLOCKFLUIDS = new ArrayList<BlockFluidBase>();

	public static final Fluid MOLTEN_IRON = new FluidMoltenIron("molten_iron", new ResourceLocation(Reference.MOD_ID, "fluids/molten_iron_still"), new ResourceLocation(Reference.MOD_ID, "fluids/molten_iron_flow"));
	
	public static void registerFluids(List<Fluid> fluids) {
		for (Fluid fluid : fluids) {
			boolean a = FluidRegistry.registerFluid(fluid);
			if (BUCKETLIST.contains(fluid))
				FluidRegistry.addBucketForFluid(fluid);
		}
	}
}
