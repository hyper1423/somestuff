package com.hyper1423.somestuff.fluids;

import java.awt.Color;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidMoltenIron extends Fluid {
	public FluidMoltenIron(String name, ResourceLocation still, ResourceLocation flowing) {
		super(name, still, flowing, Color.RED);
		setLuminosity(10);
		setDensity(6980);
		setTemperature(1811);
		setViscosity(6000);
		setGaseous(false);
		setRarity(EnumRarity.UNCOMMON);
	}
}
