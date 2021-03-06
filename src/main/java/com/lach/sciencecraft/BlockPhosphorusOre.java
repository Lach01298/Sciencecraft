package com.lach.sciencecraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockPhosphorusOre extends BlockOre {
	public BlockPhosphorusOre() {
		super();

		setHardness(4.0F);
		setStepSound(Block.soundTypeStone);
		;
		setCreativeTab(Sciencecraft.SciencecraftWorldgen);
	}

	public Item getItemDropped(int metadata, Random random, int fortune) {
		;
		int itemChooser = random.nextInt(100);

		if (itemChooser <= 50) {
			return Sciencecraft.ItemPhosphrousRed;
		} else {

			if (itemChooser < 90) {
				return Sciencecraft.ItemPhosphrousWhite;
			} else
				return Sciencecraft.ItemPhosphrousBlack;
		}

	}

	public int damagedropped(int par1) {
		return 0;
	}
}