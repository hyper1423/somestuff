package com.hyper1423.somestuff.init;

import java.util.ArrayList;
import java.util.List;

import com.hyper1423.somestuff.blocks.BlockFluidMoltenIron;
import com.hyper1423.somestuff.fluids.FluidMoltenIron;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {
	public static final NonNullList<Fluid> FLUIDS = NonNullList.<Fluid>create();

	public static final Fluid MOLTEN_IRON = new FluidMoltenIron("molten_iron",
			new ResourceLocation(Reference.MOD_ID, "fluids/molten_iron_still"), new ResourceLocation(Reference.MOD_ID, "fluids/molten_iron_flow"));

	
	public static void registerFluids(List<Fluid> fluids) {
		for (Fluid fluid : fluids) {
			FluidRegistry.registerFluid(fluid);
			FluidRegistry.addBucketForFluid(fluid);
		}
	}
}
