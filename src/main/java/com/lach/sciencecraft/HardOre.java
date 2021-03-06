package com.lach.sciencecraft;

import java.util.Random;

import com.lach.sciencecraft.Sciencecraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;

public class HardOre extends BlockOre {
	public HardOre() {
		super();

		setHardness(4.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pick", 3);
		setCreativeTab(Sciencecraft.SciencecraftWorldgen);

	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(this);
	}

}
