package com.hyper1423.somestuff.util.handlers;

import java.util.logging.Logger;

import com.hyper1423.somestuff.blocks.machines.blastfurnace.ContainerBlastFurnace;
import com.hyper1423.somestuff.blocks.machines.blastfurnace.GuiBlastFurnace;
import com.hyper1423.somestuff.blocks.machines.blastfurnace.TileEntityBlastFurnace;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		if ((world.getTileEntity(new BlockPos(x, y, z)) != null))
			if (ID == Reference.GUI_BLAST_FURNACE)
				return new ContainerBlastFurnace(player.inventory,
						(TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		if ((world.getTileEntity(new BlockPos(x, y, z)) != null))
			if (ID == Reference.GUI_BLAST_FURNACE)
				return new GuiBlastFurnace(player.inventory,
						(TileEntityBlastFurnace) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

}
