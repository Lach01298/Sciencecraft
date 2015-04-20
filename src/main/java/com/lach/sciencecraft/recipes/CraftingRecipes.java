package com.lach.sciencecraft.recipes;

import com.lach.sciencecraft.Sciencecraft;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes extends Sciencecraft {

	public static void loadRecipes() {

		GameRegistry.addRecipe(new ItemStack(Match, 16), "  W", " R ", "S  ",
				'W', ItemPhosphrousWhite, 'R', ItemPhosphrousRed, 'S',
				Items.stick);

		GameRegistry.addRecipe(new ItemStack(SciencecraftingTable, 1), "ISI",
				"CWT", "SSS", 'W', Blocks.crafting_table, 'I', IronPlate, 'S',
				SteelPlate, 'C', CopperPlate, 'T', TinPlate);

		GameRegistry.addRecipe(new ItemStack(IronPlate, 2), "II", "II", 'I',
				Items.iron_ingot);

		GameRegistry.addRecipe(new ItemStack(LeadPlate, 2), "LL", "LL", 'L',
				ItemLead);

		GameRegistry.addRecipe(new ItemStack(CopperPlate, 2), "CC", "CC", 'C',
				ItemCopper);

		GameRegistry.addRecipe(new ItemStack(TinPlate, 2), "TT", "TT", 'T',
				ItemTin);

	}
}
