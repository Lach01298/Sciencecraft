package com.lach.sciencecraft;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemGraphite extends Item {
	public ItemGraphite() {
		super();
		setMaxStackSize(64);
		setCreativeTab(Sciencecraft.Sciencecraft);
		setUnlocalizedName("Graphite");
	}

	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("graphite");
	}
}
