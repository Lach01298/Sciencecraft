package com.lach.sciencecraft.complexBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class MultiBlock extends Block {

	protected MultiBlock(Material material) {
		super(material);

	}

	public void updateMultiBlockStructure(World world, int x, int y, int z) {

	}

	public boolean isMultiBlockStructure(World world, Block block, int x,
			int y, int z, int xLength, int yLength, int zLength) {
		boolean isMultiBlock = false;
		boolean isChecking = true;

		if (world.getBlock(x, y, z) == block
				&& world.getBlock(x + 1, y, z) == block)
			;

		return false;
	}

}
