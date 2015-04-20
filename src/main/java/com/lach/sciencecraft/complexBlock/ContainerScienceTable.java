package com.lach.sciencecraft.complexBlock;

import com.lach.sciencecraft.entitys.TileEntityBunsen;
import com.lach.sciencecraft.entitys.TileEntityScienceTable;
import com.lach.sciencecraft.inventory.slot.SlotScienceTable;
import com.lach.sciencecraft.recipes.STRecipeHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class ContainerScienceTable extends Container {

	private TileEntityScienceTable table;
	private World worldObj;
	
	IInventory output = new InventoryCraftResult();
	public InventoryCrafting inputs = new InventoryCrafting(this, 4, 4);
	
	
	
	
	public ContainerScienceTable(InventoryPlayer invPlayer,TileEntityScienceTable table) 
	{
		this.table = table;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.addSlotToContainer(new Slot(this.inputs, j + i * 4, 12 + j * 18,7 + i * 18));
			}
		}
		this.addSlotToContainer(new SlotScienceTable(invPlayer.player, this.inputs, output, 16, 124, 35));        //16, 124, 35

		
		
		//player invetory
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer,  9 + j + i * 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		 this.onCraftMatrixChanged(this.inputs);
		
		
	}

	/**
     * Callback for when the grid is changed.
     */
    public void onCraftMatrixChanged(IInventory input)
    {
        super.onCraftMatrixChanged(input);
    	this.output.setInventorySlotContents(0, STRecipeHandler.getInstance().findMatchingRecipe(input, this.worldObj));
    }
	
	
	
	
	
	public boolean canInteractWith(EntityPlayer player) {

		return this.table.isUseableByPlayer(player);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotID) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotID == 0) {
				if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (slotID >= 10 && slotID < 37) {
				if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
					return null;
				}
			} else if (slotID >= 37 && slotID < 46) {
				if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
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

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}


	 





}
