package com.lach.sciencecraft;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

import com.lach.sciencecraft.Sciencecraft;
import com.lach.sciencecraft.entitys.TileEntityBunsen;
import com.lach.sciencecraft.entitys.TileEntityScienceTable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BlockSciencecraftingTable extends BlockContainer {

	private IIcon topIcon;
	private IIcon sideIcon;
	private final Random random = new Random();

	public BlockSciencecraftingTable() {
		super(Material.iron);

		setHardness(4.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pick", 2);
		setCreativeTab(Sciencecraft.Sciencecraft);

	}

	public void registerBlockIcons(IIconRegister Icon) {

		this.topIcon = Icon.registerIcon(this.getTextureName() + "Top");
		this.sideIcon = Icon.registerIcon(this.getTextureName() + " sides");
	}

	public IIcon getIcon(int side, int meta) {
		switch (side) {
		case 0:
			return topIcon;
		case 1:
			return topIcon;
		default:
			return sideIcon;

		}

	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(this);
	}

	public void breakBlock(World world, int x, int y, int z, Block block,
			int metadata) {

		TileEntityScienceTable ScienceTable = (TileEntityScienceTable) world
				.getTileEntity(x, y, z);

		if (ScienceTable != null) {
			for (int i1 = 0; i1 < ScienceTable.getSizeInventory(); ++i1) {
				ItemStack itemstack = ScienceTable.getStackInSlot(i1);

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

		return new TileEntityScienceTable();
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, Sciencecraft.instance,
					Sciencecraft.ScienceTable_ID, world, x, y, z);
		}

		return true;
	}
}