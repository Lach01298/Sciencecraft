package com.lach.sciencecraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMatch extends Item {
	public ItemMatch() {
		super();
		setMaxStackSize(64);
		setCreativeTab(Sciencecraft.Sciencecraft);
		setUnlocalizedName("Match");
	}

	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("Sciencecraft:Match");
	}

	public boolean onItemUse(ItemStack ItemStack, EntityPlayer EntityPlayer,
			World World, int x, int y, int z, int face, float xHit, float yHit,
			float zHit) {
		if (face == 0) {
			--y;
		}

		if (face == 1) {
			++y;
		}

		if (face == 2) {
			--z;
		}

		if (face == 3) {
			++z;
		}

		if (face == 4) {
			--x;
		}

		if (face == 5) {
			++x;
		}

		if (!EntityPlayer.canPlayerEdit(x, y, z, face, ItemStack)) {
			return false;
		} else {
			if (World.isAirBlock(x, y, z)) {
				World.playSoundEffect((double) x + 0.5D, (double) y + 0.5D,
						(double) z + 0.5D, "fire.ignite", 1.0F,
						itemRand.nextFloat() * 0.4F + 0.8F);
				World.setBlock(x, y, z, Blocks.fire);
				if (EntityPlayer.capabilities.isCreativeMode) {

				} else {
					ItemStack.stackSize = ItemStack.stackSize - 1;
				}

			}

			ItemStack.damageItem(1, EntityPlayer);
			return true;
		}
	}
}
