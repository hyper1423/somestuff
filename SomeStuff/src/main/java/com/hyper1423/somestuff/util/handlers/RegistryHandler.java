package com.hyper1423.somestuff.util.handlers;

import java.util.List;

import com.hyper1423.somestuff.Main;
import com.hyper1423.somestuff.init.ModBlocks;
import com.hyper1423.somestuff.init.ModFluids;
import com.hyper1423.somestuff.init.ModItems;
import com.hyper1423.somestuff.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {

		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	/** This method registers fluids.
	 * 
	 * @param fluids
	 * @param blockfluids
	 */
	public static void registerFluids(List<Fluid> fluids) {
		FluidRegistry.enableUniversalBucket();
		for (Fluid fluid : fluids) {
			FluidRegistry.registerFluid(fluid);
			FluidRegistry.addBucketForFluid(fluid);
		}
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {

		for (Item item : ModItems.ITEMS) {
			Main.proxy.registerItemRenderer(item, 0, "inventory");
		}
		
		for (Block block : ModBlocks.BLOCKS) {
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
	}
	
	public static void preInitRegistries() {
		registerFluids(ModFluids.FLUIDS);
	}
	
	public static void initRegistries() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void postInitRegistries() {
		
	}
}
