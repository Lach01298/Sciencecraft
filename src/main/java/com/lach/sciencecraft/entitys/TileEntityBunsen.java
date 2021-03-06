package com.lach.sciencecraft.entitys;

import java.util.List;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.brewing.PotionBrewedEvent;

public class TileEntityBunsen extends TileEntity implements ISidedInventory {

	private ItemStack[] slots = new ItemStack[4];

	private static final int[] Top = new int[] { 0 };
	private static final int[] Bottom = new int[] { 2 };
	private static final int[] Side = new int[] { 1 };
	/** The number of ticks that the current item has been burning for */
	public int Burn;
	/** the number of ticks the fuel has left */
	public int FuelTime;
	/** the number of ticks a item has been item */
	public int time;

	public void updateEntity() {
		boolean flag = this.Burn > 0;
		boolean flag1 = false;

		if (this.Burn > 0) {
			--this.Burn;
		}

		if (!this.worldObj.isRemote) {
			if (this.Burn == 0 && this.canSmelt()) {
				this.time = this.Burn = TileEntityFurnace
						.getItemBurnTime(this.slots[1]);

				if (this.Burn > 0) {
					flag1 = true;

					if (this.slots[1] != null) {
						--this.slots[1].stackSize;

						if (this.slots[1].stackSize == 0) {
							this.slots[1] = slots[1].getItem()
									.getContainerItem(slots[1]);
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt()) {
				++this.time;

				if (this.time == 200) {
					this.time = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.time = 0;
			}

			if (flag != this.Burn > 0) {
				flag1 = true;

			}
		}

		if (flag1) {
			this.markDirty();
		}
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];

		this.Burn = compound.getShort("Burn");
		this.FuelTime = compound.getShort("FuelTime");
		this.time = compound.getShort("TimeLeft");

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b = nbttagcompound1.getByte("Slot");

			if (b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setShort("Burn", (short) this.Burn);
		compound.setShort("FuelTime", (short) this.FuelTime);
		compound.setShort("TimeLeft", (short) this.time);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.slots.length; ++i) {
			if (this.slots[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		compound.setTag("Items", nbttaglist);

	}

	@Override
	public int getSizeInventory() {

		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {

		return this.slots[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int decreaseAmount) {

		if (this.slots[slot] != null) {
			ItemStack itemStack;

			if (this.slots[slot].stackSize <= decreaseAmount) {
				itemStack = this.slots[slot];
				this.slots[slot] = null;
				return itemStack;
			} else {
				itemStack = this.slots[slot].splitStack(decreaseAmount);

				if (this.slots[slot].stackSize == 0) {
					this.slots[slot] = null;
				}
			}
			return itemStack;
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {

		if (this.slots[slot] != null) {
			ItemStack itemstack = this.slots[slot];
			this.slots[slot] = null;
			return itemstack;
		}

		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {

		this.slots[slot] = itemStack;

		if (itemStack != null
				&& itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {

		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {

		return false;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {

		switch (slot) {
		case 0:
			return true;

		case 1:
			return TileEntityFurnace.isItemFuel(itemStack);

		default:
			return false;
		}

	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {

		switch (side) {
		case 0:
			return Bottom;

		case 1:
			return Top;

		default:
			return Side;
		}

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int side) {

		return this.isItemValidForSlot(slot, itemStack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack ItemStack, int side) {

		switch (side) {
		case 0:
			if (slot == Bottom[0]) {
				return true;
			} else {
				return false;
			}
		case 1:
			return false;

		default:
			return false;
		}
	}

	public int getBurnTime() {
		return this.Burn;
	}

	public int getBurnTimeRemainingScaled(int scale) {
		return this.time * scale / 200;

	}

	public boolean isBurning() {
		return this.Burn > 0;
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
					this.slots[0]);

			if (this.slots[0] == null) {
				this.slots[0] = itemstack.copy();
			} else if (this.slots[0].getItem() == itemstack.getItem()) {
				this.slots[0].stackSize += itemstack.stackSize; // Forge BugFix:
																// Results may
																// have multiple
																// items
			}

			--this.slots[0].stackSize;

			if (this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
	}

	private boolean canSmelt() {
		if (this.slots[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
					this.slots[2]);
			if (itemstack == null)
				return false;
			if (this.slots[0] == null)
				return true;
			if (!this.slots[0].isItemEqual(itemstack))
				return false;
			int result = slots[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit()
					&& result <= this.slots[2].getMaxStackSize(); // Forge
																	// BugFix:
																	// Make it
																	// respect
																	// stack
																	// sizes
																	// properly.
		}
	}
}
