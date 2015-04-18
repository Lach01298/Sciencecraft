package com.lach.sciencecraft.complexBlock;

import com.lach.sciencecraft.entitys.TileEntityBunsen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class BunsenGUI extends GuiContainer {

	public final ResourceLocation texture = new ResourceLocation(
			"sciencecraft", "/textures/gui/bunsen_GUI.png");

	private TileEntityBunsen bunsen;

	public BunsenGUI(InventoryPlayer inventoryPlayer, TileEntityBunsen bunsen) {
		super(new ContainerBunsen(inventoryPlayer, bunsen));

		this.xSize = 176;
		this.ySize = 166;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int i1;

		i1 = this.bunsen.getBurnTimeRemainingScaled(12);
		this.drawTexturedModalRect(guiLeft + 100, guiTop + 17 + 12 - i1, 176,
				0 - i1, 9, i1 + 2);

	}

}
