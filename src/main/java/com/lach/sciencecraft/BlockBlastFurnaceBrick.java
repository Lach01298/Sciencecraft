package com.lach.sciencecraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockBlastFurnaceBrick extends Block {
	public BlockBlastFurnaceBrick(int id, Material material) {
		super(material);
		setHardness(0.5F).setStepSound(Block.soundTypeStone).setCreativeTab(
				Sciencecraft.Sciencecraft);
	}

	public int isBlockSame(World world, int x, int y, int z, Block block) {
		if (world.getBlock(x, y, z) == block) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int face, float f1, float f2, float f3) {
		updateMultiBlockStructure(world, x, y, z);
		return false;
	}

	public void updateMultiBlockStructure(World world, int x, int y, int z) {
		isMultiBlockStructure(world, Sciencecraft.BlastFurnaceBrick, x, y, z);
	}

	public boolean isMultiBlockStructure(World world, Block block, int x,
			int y, int z) {
		int BlockCount = 0;
		BlockCount = BlockCount + isBlockSame(world, x, y, z, block);
		BlockCount = BlockCount + isBlockSame(world, x + 1, y, z, block);
		BlockCount = BlockCount + isBlockSame(world, x - 1, y, z, block);

		BlockCount = BlockCount + isBlockSame(world, x, y, z + 1, block);
		BlockCount = BlockCount + isBlockSame(world, x + 1, y, z + 1, block);
		BlockCount = BlockCount + isBlockSame(world, x - 1, y, z + 1, block);

		BlockCount = BlockCount + isBlockSame(world, x, y, z - 1, block);
		BlockCount = BlockCount + isBlockSame(world, x + 1, y, z - 1, block);
		BlockCount = BlockCount + isBlockSame(world, x - 1, y, z - 1, block);

		BlockCount = BlockCount + isBlockSame(world, x + 1, y + 1, z, block);
		BlockCount = BlockCount + isBlockSame(world, x - 1, y + 1, z, block);

		BlockCount = BlockCount + isBlockSame(world, x, y + 1, z + 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x + 1, y + 1, z + 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x - 1, y + 1, z + 1, block);

		BlockCount = BlockCount + isBlockSame(world, x, y + 1, z - 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x + 1, y + 1 + 1, z - 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x - 1, y + 1 + 1, z - 1, block);

		BlockCount = BlockCount + isBlockSame(world, x + 1, y + 2, z, block);
		BlockCount = BlockCount + isBlockSame(world, x - 1, y + 2, z, block);

		BlockCount = BlockCount + isBlockSame(world, x, y + 2, z + 1, block);
		BlockCount = BlockCount + isBlockSame(world, x + 1, y, z + 1, block);
		BlockCount = BlockCount + isBlockSame(world, x - 1, y, z + 1, block);

		BlockCount = BlockCount + isBlockSame(world, x, y + 2, z - 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x + 1, y + 2, z - 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x - 1, y + 2, z - 1, block);

		BlockCount = BlockCount
				+ isBlockSame(world, x + 1, y - 1, z + 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x + 1, y - 1, z - 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x - 1, y - 1, z - 1, block);
		BlockCount = BlockCount
				+ isBlockSame(world, x - 1, y - 1, z + 1, block);

		if (BlockCount == 29) {

			return true;
		} else {
			return false;
		}

	}

}