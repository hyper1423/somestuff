package com.hyper1423.somestuff.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.hyper1423.somestuff.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class BlastFurnaceRecipes {
	private static final BlastFurnaceRecipes INSTANCE = new BlastFurnaceRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static BlastFurnaceRecipes getInstance() {
		return INSTANCE;
	}

	private BlastFurnaceRecipes() { }

	public void addRecipe(ItemStack input, ItemStack result, float experience) {
		if (getResult(input) != ItemStack.EMPTY) throw new IllegalArgumentException("Cannot create recipe from already existing recipe");
		smeltingList.put(input, result);
		experienceList.put(result, Float.valueOf(experience));
	}

	public ItemStack getResult(ItemStack input) {
		for (Entry<ItemStack, ItemStack> entry : smeltingList.entrySet()) {
			if (ItemStack.areItemStacksEqual(input, (ItemStack) entry.getKey())) {
				return (ItemStack) entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}

	public float getResultExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (ItemStack.areItemStacksEqual(stack, (ItemStack) entry.getKey())) {
				return ((Float) entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}