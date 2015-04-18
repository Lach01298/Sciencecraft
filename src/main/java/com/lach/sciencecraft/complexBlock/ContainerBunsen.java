package com.lach.sciencecraft.complexBlock;

import com.lach.sciencecraft.entitys.TileEntityBunsen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerBunsen extends Container {

	private TileEntityBunsen bunsen;
	public int craftingTime;

	public ContainerBunsen(InventoryPlayer inventoryPlayer,
			TileEntityBunsen bunsen) {
		this.bunsen = bunsen;

		this.addSlotToContainer(new Slot(bunsen, 0, 79, 17));
		this.addSlotToContainer(new Slot(bunsen, 1, 79, 53));
		this.addSlotToContainer(new Slot(bunsen, 2, 31, 53));
		this.addSlotToContainer(new Slot(bunsen, 3, 113, 17));

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18,
					142));
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventoryPlayer,
						9 + j + i * 9, 8 + j * 18, 84 + i * 18));
			}
		}
	}

	public boolean canInteractWith(EntityPlayer player) {

		return this.bunsen.isUseableByPlayer(player);
	}

	public ItemStack transferStackInSlot(EntityPlayer EntityPlayer, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(i);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (i == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (i != 1 && i != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityFurnace.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (i >= 3 && i < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (i >= 30 && i < 39
						&& !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(EntityPlayer, itemstack1);
		}

		return itemstack;
	}

}
