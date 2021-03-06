package com.lach.sciencecraft;

import java.util.Random;

import com.lach.sciencecraft.entitys.TileEntityBunsen;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class BlockBunsen extends BlockContainer {

	private final Random random = new Random();

	protected BlockBunsen(Material material) {
		super(material);

		setCreativeTab(Sciencecraft.Sciencecraft);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, Sciencecraft.instance,
					Sciencecraft.Bunsen_ID, world, x, y, z);
		}

		return true;
	}

	public void breakBlock(World world, int x, int y, int z, Block block,
			int metadata) {

		TileEntityBunsen Bunsen = (TileEntityBunsen) world.getTileEntity(x, y,
				z);

		if (Bunsen != null) {
			for (int i1 = 0; i1 < Bunsen.getSizeInventory(); ++i1) {
				ItemStack itemstack = Bunsen.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					float f2 = this.random.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(world,
								(double) ((float) x + f),
								(double) ((float) y + f1),
								(double) ((float) z + f2), new ItemStack(
										itemstack.getItem(), j1,
										itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound(
									(NBTTagCompound) itemstack.getTagCompound()
											.copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random
								.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random
								.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random
								.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityBunsen();
	}

	public int onBlockPlaced(World world, int x, int y, int z, int side,
			float HitX, float HitY, float HitZ, int Metadata) {

		return Metadata;

	}

}