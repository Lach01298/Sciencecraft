package com.lach.sciencecraft.recipes;

import com.lach.sciencecraft.Sciencecraft;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class SmeltingRecipes extends Sciencecraft {
	public static void loadRecipes() {
		GameRegistry.addSmelting(CopperOre, new ItemStack(ItemCopper), 0.1f);
		GameRegistry.addSmelting(TinOre, new ItemStack(ItemTin), 0.1f);
		GameRegistry.addSmelting(LeadOre, new ItemStack(ItemLead), 0.1f);
		GameRegistry.addSmelting(ZincOre, new ItemStack(ItemZinc), 0.1f);
		GameRegistry.addSmelting(SilverOre, new ItemStack(ItemSilver), 0.1f);

	}
}
