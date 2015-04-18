package com.lach.sciencecraft;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemNotes extends Item {
	public ItemNotes() {
		super();
		setMaxStackSize(1);
		setCreativeTab(Sciencecraft.Sciencecraft);
		setUnlocalizedName("notes");
	}

	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("Sciencecraft:notes");
	}
}
