package com.hyper1423.somestuff.util.handlers;

import java.util.logging.Logger;

import com.hyper1423.somestuff.container.ContainerAlloyingMachine;
import com.hyper1423.somestuff.container.ContainerBlastFurnace;
import com.hyper1423.somestuff.container.gui.GuiAlloyingMachine;
import com.hyper1423.somestuff.container.gui.GuiBlastFurnace;
import com.hyper1423.somestuff.tileentities.TileEntityAlloyingMachine;
import com.hyper1423.somestuff.tileentities.TileEntityBlastFurnace;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if ((world.getTileEntity(new BlockPos(x, y, z)) != null))
			if (ID == Reference.GUI_BLAST_FURNACE)
				return new ContainerBlastFurnace(player.inventory,
						(TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z)));
			else if (ID == Reference.GUI_ALLOYING_MACHINE)
				return new ContainerAlloyingMachine(player.inventory,
						(TileEntityAlloyingMachine) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if ((world.getTileEntity(new BlockPos(x, y, z)) != null))
			if (ID == Reference.GUI_BLAST_FURNACE)
				return new GuiBlastFurnace(player.inventory,
						(TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z)));
			else if (ID == Reference.GUI_ALLOYING_MACHINE)
				return new GuiAlloyingMachine(player.inventory,
						(TileEntityAlloyingMachine) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

}
