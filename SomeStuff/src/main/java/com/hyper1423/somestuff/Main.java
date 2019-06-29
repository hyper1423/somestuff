package com.hyper1423.somestuff;

import com.hyper1423.somestuff.blocks.machines.blastfurnace.TileEntityBlastFurnace;
import com.hyper1423.somestuff.init.ModRecipes;
import com.hyper1423.somestuff.proxy.CommonProxy;
import com.hyper1423.somestuff.tabs.SSTab;
import com.hyper1423.somestuff.util.Reference;
import com.hyper1423.somestuff.util.handlers.RegistryHandler;
import com.hyper1423.somestuff.world.ModWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	public static final CreativeTabs someStuff = new SSTab("somestufftab");

	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInitRegistries();
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		RegistryHandler.initRegistries();
		ModRecipes.init();
	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries();
	}
}