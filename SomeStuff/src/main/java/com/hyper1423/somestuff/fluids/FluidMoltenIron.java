package com.hyper1423.somestuff.fluids;

import java.awt.Color;

import com.hyper1423.somestuff.init.ModFluids;

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
		setFillSound(new SoundEvent(new ResourceLocation("minecraft", "item.bucket.fill_lava")));
		setEmptySound(new SoundEvent(new ResourceLocation("minecraft", "item.bucket.empty_lava")));
		
		ModFluids.FLUIDS.add(this);
	}
}
