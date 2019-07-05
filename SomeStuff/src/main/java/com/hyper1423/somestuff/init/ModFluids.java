package com.hyper1423.somestuff.init;

import java.util.ArrayList;
import java.util.List;

import com.hyper1423.somestuff.blocks.BlockFluidMoltenIron;
import com.hyper1423.somestuff.fluids.FluidMoltenIron;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class ModFluids {
	public static final List<Fluid> FLUIDS = new ArrayList<Fluid>();

	public static final Fluid MOLTEN_IRON = new FluidMoltenIron("molten_iron",
			new ResourceLocation(Reference.MOD_ID, "molten_iron_still"), new ResourceLocation(Reference.MOD_ID, "molten_iron_flowing"));
	public static final BlockFluidFinite BLOCK_MOLTEN_IRON = new BlockFluidMoltenIron(MOLTEN_IRON);
}
