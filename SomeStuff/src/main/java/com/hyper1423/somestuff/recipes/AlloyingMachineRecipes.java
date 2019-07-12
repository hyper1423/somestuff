package com.hyper1423.somestuff.recipes;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class AlloyingMachineRecipes {

	private static final AlloyingMachineRecipes INSTANCE = new AlloyingMachineRecipes();
	private final Table<FluidStack, FluidStack, FluidStack> craftingRecipes = HashBasedTable
			.<FluidStack, FluidStack, FluidStack>create();
	private final Map<ItemStack, FluidStack> meltingRecipes = Maps.<ItemStack, FluidStack>newHashMap();

	/**
	 * Returns an instance of the class
	 * 
	 * @return
	 */
	public static AlloyingMachineRecipes getInstance() {

		return INSTANCE;
	}

	private AlloyingMachineRecipes() {
//		this.addRecipe(ModFluids.MOLTEN_COPPER, ModFluids.MOLTEN_TIN, ModFluids.MOLTEN_BRONZE);
	}
	
	public void addAlloyingRecipe(FluidStack fluid1, FluidStack fluid2, FluidStack result) {
		if (getAlloyingResult(fluid1, fluid2) != null) throw new IllegalArgumentException("Cannot create recipe from already existing recipe");
		craftingRecipes.put(fluid1, fluid2, result);
	}

	/**
	 * Returns result of alloying a fluid to another fluid
	 * 
	 * @param fluid1
	 * @param fluid2
	 * @return Alloy of fluid1 and fluid2
	 */
	public FluidStack getAlloyingResult(FluidStack fluid1, FluidStack fluid2) {
		// fluid1 + fluid2
		for (Entry<FluidStack, Map<FluidStack, FluidStack>> colEntry : craftingRecipes.columnMap().entrySet()) {
			if (fluid1.isFluidStackIdentical(colEntry.getKey())) {
				for (Entry<FluidStack, FluidStack> rowEntry : colEntry.getValue().entrySet()) {
					if (fluid2.isFluidStackIdentical(rowEntry.getValue())) {
						return rowEntry.getValue();
					}
				}
			}
		}
		// fluid2 + fluid1
		for (Entry<FluidStack, Map<FluidStack, FluidStack>> rowEntry : craftingRecipes.rowMap().entrySet()) {
			if (fluid1.isFluidStackIdentical(rowEntry.getKey())) {
				for (Entry<FluidStack, FluidStack> colEntry : rowEntry.getValue().entrySet()) {
					if (fluid2.isFluidStackIdentical(colEntry.getValue())) {
						return colEntry.getValue();
					}
				}
			}
		}
		return null;
	}
	
	public FluidStack getMeltingResult(ItemStack stack) {
		for (Entry<ItemStack, FluidStack> entry : this.meltingRecipes.entrySet()) {
			if (ItemStack.areItemStacksEqual(stack, entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}
}
