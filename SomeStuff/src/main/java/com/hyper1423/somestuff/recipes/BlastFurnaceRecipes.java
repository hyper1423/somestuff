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

	private BlastFurnaceRecipes() {
		addRecipe(new ItemStack(Blocks.ACACIA_FENCE),
				new ItemStack(ModItems.SLAG), 5.0F);
	}

	public void addRecipe(ItemStack input, ItemStack result, float experience) {
		if (getResult(input) != ItemStack.EMPTY)
			return;
		smeltingList.put(input, result);
		experienceList.put(result, Float.valueOf(experience));
	}

	public ItemStack getResult(ItemStack input) {
		for (Entry<ItemStack, ItemStack> entry : smeltingList.entrySet()) {
			if (compareItemStacks(input, (ItemStack) entry.getKey())) {
				return (ItemStack) entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem()
				&& (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Map<ItemStack, ItemStack> getDualSmeltingList() {
		return this.smeltingList;
	}

	public float getResultExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack) entry.getKey())) {
				return ((Float) entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}