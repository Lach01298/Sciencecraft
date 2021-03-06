package com.lach.sciencecraft.complexBlock;

import com.lach.sciencecraft.entitys.TileEntityBunsen;
import com.lach.sciencecraft.entitys.TileEntityScienceTable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GUIScienceTable extends GuiContainer {

	public final ResourceLocation texture = new ResourceLocation(
			"sciencecraft", "/textures/gui/scienceTable GUI.png");

	public GUIScienceTable(InventoryPlayer inventoryPlayer,
			TileEntityScienceTable table) {
		super(new ContainerScienceTable(inventoryPlayer, table));

		this.xSize = 176;
		this.ySize = 166;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}
