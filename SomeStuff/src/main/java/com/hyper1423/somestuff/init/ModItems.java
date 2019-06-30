package com.hyper1423.somestuff.init;

import java.util.ArrayList;
import java.util.List;

import com.hyper1423.somestuff.items.ItemBase;
import com.hyper1423.somestuff.items.armor.ArmorBase;
import com.hyper1423.somestuff.items.tools.ToolAxe;
import com.hyper1423.somestuff.items.tools.ToolHammer;
import com.hyper1423.somestuff.items.tools.ToolHoe;
import com.hyper1423.somestuff.items.tools.ToolPickaxe;
import com.hyper1423.somestuff.items.tools.ToolSpade;
import com.hyper1423.somestuff.items.tools.ToolSword;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//materials
	public static final ToolMaterial MATERIAL_COPPER = EnumHelper.addToolMaterial("material_copper", 2, 300, 6.0F, 2.0F, 14);
	public static final ToolMaterial MATERIAL_OBSIDIAN = EnumHelper.addToolMaterial("material_obsidian", 3, 5000, 8.0F, 11F, 10);
	public static final ArmorMaterial ARMOR_MATERIAL_OBSIDIAN = EnumHelper.addArmorMaterial("material_obsidian", "somestuff:obsidian", 5000,
			new int[] {6, 12, 8, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
	
	//items
	public static final Item SLAG = new ItemBase("slag");
	
	
	//plates
	public static final Item COPPER_PLATE = new ItemBase("copper_plate");
	
	
	//ingots
	public static final Item PLASTIC_INGOT = new ItemBase("plastic_ingot");
	public static final Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot");
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot");
	
	
	//tools
	public static final ItemSword OBSIDIAN_SWORD = new ToolSword("obsidian_sword", MATERIAL_OBSIDIAN);
	public static final ItemSpade OBSIDIAN_SHOVEL = new ToolSpade("obsidian_shovel", MATERIAL_OBSIDIAN);
	public static final ItemPickaxe OBSIDIAN_PICKAXE = new ToolPickaxe("obsidian_pickaxe", MATERIAL_OBSIDIAN);
	public static final ItemHoe OBSIDIAN_HOE = new ToolHoe("obsidian_hoe", MATERIAL_OBSIDIAN);
	public static final ItemAxe OBSIDIAN_AXE = new ToolAxe("obsidian_axe", MATERIAL_OBSIDIAN);
	public static final ItemTool COPPER_HAMMER = new ToolHammer("copper_hammer", MATERIAL_COPPER);
	
	
	//armors
	public static final Item OBSIDIAN_HELMET = new ArmorBase("obsidian_helmet", ARMOR_MATERIAL_OBSIDIAN, 1, EntityEquipmentSlot.HEAD);
	public static final Item OBSIDIAN_CHESTPLATE = new ArmorBase("obsidian_chestplate", ARMOR_MATERIAL_OBSIDIAN, 1, EntityEquipmentSlot.CHEST);
	public static final Item OBSIDIAN_LEGGINGS = new ArmorBase("obsidian_leggings", ARMOR_MATERIAL_OBSIDIAN, 2, EntityEquipmentSlot.LEGS);
	public static final Item OBSIDIAN_BOOTS = new ArmorBase("obsidian_boots", ARMOR_MATERIAL_OBSIDIAN, 1, EntityEquipmentSlot.FEET);
}
