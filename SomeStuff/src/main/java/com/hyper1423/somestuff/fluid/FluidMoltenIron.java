package com.hyper1423.somestuff.fluid;

import java.awt.Color;

import com.hyper1423.somestuff.init.ModFluids;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;

public class FluidMoltenIron extends Fluid {
	public FluidMoltenIron(String name, ResourceLocation still, ResourceLocation flowing) {
		super(name, still, flowing);
		setLuminosity(10);
		setDensity(6980);
		setTemperature(1811);
		setViscosity(6000);
		setGaseous(false);
		setRarity(EnumRarity.UNCOMMON);
		
		setFillSound(SoundEvents.ITEM_BUCKET_FILL_LAVA);
		setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
		
		ModFluids.FLUIDS.add(this);
		ModFluids.BUCKETLIST.add(this);
	}
}
