package com.lach.sciencecraft.complexBlock;

import com.lach.sciencecraft.Sciencecraft;
import com.lach.sciencecraft.entitys.TileEntityBunsen;
import com.lach.sciencecraft.entitys.TileEntityScienceTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class SciencecraftGUIHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		switch (ID) {
		case Sciencecraft.ScienceTable_ID:
			if (tileEntity instanceof TileEntityScienceTable) {
				return new ContainerScienceTable(player.inventory,
						(TileEntityScienceTable) tileEntity);
			}

		case Sciencecraft.Bunsen_ID:
			if (tileEntity instanceof TileEntityBunsen) {
				return new ContainerBunsen(player.inventory,
						(TileEntityBunsen) tileEntity);
			}

		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		switch (ID) {
		case Sciencecraft.ScienceTable_ID:
			if (tileEntity instanceof TileEntityScienceTable) {
				return new GUIScienceTable(player.inventory,
						(TileEntityScienceTable) tileEntity);
			}

		case Sciencecraft.Bunsen_ID:
			if (tileEntity instanceof TileEntityBunsen) {
				return new BunsenGUI(player.inventory,
						(TileEntityBunsen) tileEntity);
			}

		}

		return null;
	}

}
