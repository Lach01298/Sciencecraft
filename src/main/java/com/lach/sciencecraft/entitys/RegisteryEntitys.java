package com.lach.sciencecraft.entitys;

import com.lach.sciencecraft.Sciencecraft;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegisteryEntitys extends Sciencecraft {

	public static void loadTileEntitys() {
		GameRegistry.registerTileEntity(TileEntityScienceTable.class,
				"ScienceTable");

	}

	public static void loadEntitys() {

	}

}