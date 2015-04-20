package com.lach.sciencecraft.entitys;

import com.lach.sciencecraft.inventory.InventoryCrafter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityScienceTable extends TileEntity implements
		ISidedInventory {

	private ItemStack[] slots = new ItemStack[16];

	;

	private static final int[] Top = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15 };
	private static final int[] Bottom = new int[] { 16 };
	private static final int[] Side = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15 };

	public void updateEntity() {

	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b = nbttagcompound1.getByte("Slot");

			if (b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound compound) {

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
		case 16:
			return true;
		default:
			return true;
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

}