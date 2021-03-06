package com.lach.sciencecraft.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderBusen extends TileEntitySpecialRenderer {

	private ResourceLocation Texture = new ResourceLocation("sciencecraft",
			"textures/blocks/Bunsen model.png");

	private double TextureH = 32;
	private double TextureW = 32;
	private double Pixel = 0.0625d;

	@Override
	public void renderTileEntityAt(TileEntity tile, double Xp, double Yp,
			double Zp, float f) {

		int X = tile.xCoord;
		int Y = tile.yCoord;
		int Z = tile.zCoord;

		GL11.glPushMatrix();
		GL11.glTranslatef((float) Xp, (float) Yp, (float) Zp);

		Tessellator tess = Tessellator.instance;
		this.bindTexture(Texture);

		tess.startDrawingQuads();// teslator start

		tess.addVertexWithUV(6 * Pixel, 0, 6 * Pixel, 21 * (1 / TextureW),
				16 * (1 / TextureH)); // tube
		tess.addVertexWithUV(6 * Pixel, 1, 6 * Pixel, 21 * (1 / TextureW), 0);
		tess.addVertexWithUV(10 * Pixel, 1, 6 * Pixel, 17 * (1 / TextureW), 0);
		tess.addVertexWithUV(10 * Pixel, 0, 6 * Pixel, 17 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.addVertexWithUV(6 * Pixel, 0, 10 * Pixel, 21 * (1 / TextureW),
				16 * (1 / TextureH));
		tess.addVertexWithUV(6 * Pixel, 1, 10 * Pixel, 21 * (1 / TextureW), 0);
		tess.addVertexWithUV(6 * Pixel, 1, 6 * Pixel, 17 * (1 / TextureW), 0);
		tess.addVertexWithUV(6 * Pixel, 0, 6 * Pixel, 17 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.addVertexWithUV(10 * Pixel, 0, 6 * Pixel, 21 * (1 / TextureW),
				16 * (1 / TextureH));
		tess.addVertexWithUV(10 * Pixel, 1, 6 * Pixel, 21 * (1 / TextureW), 0);
		tess.addVertexWithUV(10 * Pixel, 1, 10 * Pixel, 17 * (1 / TextureW), 0);
		tess.addVertexWithUV(10 * Pixel, 0, 10 * Pixel, 17 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.addVertexWithUV(10 * Pixel, 0, 10 * Pixel, 21 * (1 / TextureW),
				16 * (1 / TextureH));
		tess.addVertexWithUV(10 * Pixel, 1, 10 * Pixel, 21 * (1 / TextureW), 0);
		tess.addVertexWithUV(6 * Pixel, 1, 10 * Pixel, 17 * (1 / TextureW), 0);
		tess.addVertexWithUV(6 * Pixel, 0, 10 * Pixel, 17 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.addVertexWithUV(1, Pixel, 0, 16 * (1 / TextureW),
				16 * (1 / TextureH));// base top
		tess.addVertexWithUV(0, Pixel, 0, 16 * (1 / TextureW), 0);
		tess.addVertexWithUV(0, Pixel, 1, 0, 0);
		tess.addVertexWithUV(1, Pixel, 1, 0, 16 * (1 / TextureH));

		tess.addVertexWithUV(0, 0, 0, (1 / TextureW), (1 / TextureH)); // base
																		// side
		tess.addVertexWithUV(0, Pixel, 0, (1 / TextureW), 0);
		tess.addVertexWithUV(1, Pixel, 0, 0, 0);
		tess.addVertexWithUV(1, 0, 0, 0, (1 / TextureH));

		tess.addVertexWithUV(1, 0, 1, (1 / TextureW), (1 / TextureH));
		tess.addVertexWithUV(1, Pixel, 1, (1 / TextureW), 0);
		tess.addVertexWithUV(0, Pixel, 1, 0, 0);
		tess.addVertexWithUV(0, 0, 1, 0, (1 / TextureH));

		tess.addVertexWithUV(0, 0, 1, (1 / TextureW), (1 / TextureH));
		tess.addVertexWithUV(0, Pixel, 1, (1 / TextureW), 0);
		tess.addVertexWithUV(0, Pixel, 0, 0, 0);
		tess.addVertexWithUV(0, 0, 0, 0, (1 / TextureH));

		tess.addVertexWithUV(1, 0, 0, (1 / TextureW), (1 / TextureH));
		tess.addVertexWithUV(1, Pixel, 0, (1 / TextureW), 0);
		tess.addVertexWithUV(1, Pixel, 1, 0, 0);
		tess.addVertexWithUV(1, 0, 1, 0, (1 / TextureH));

		tess.addVertexWithUV(0, 0, 0, 16 * (1 / TextureW), 16 * (1 / TextureH)); // base
																					// bottom
		tess.addVertexWithUV(1, 0, 0, 16 * (1 / TextureW), 0);
		tess.addVertexWithUV(1, 0, 1, 0, 0);
		tess.addVertexWithUV(0, 0, 1, 0, 16 * (1 / TextureH));

		tess.addVertexWithUV(6 * Pixel, 1, 6 * Pixel, 24 * (1 / TextureW),
				(1 / TextureH)); // tube top
		tess.addVertexWithUV(6 * Pixel, 1, 1 - 6 * Pixel, 24 * (1 / TextureW),
				0);
		tess.addVertexWithUV(7 * Pixel, 1, 1 - 6 * Pixel, 22 * (1 / TextureW),
				0);
		tess.addVertexWithUV(7 * Pixel, 1, 6 * Pixel, 22 * (1 / TextureW),
				(1 / TextureH));

		tess.addVertexWithUV(9 * Pixel, 1, 6 * Pixel, 24 * (1 / TextureW),
				(1 / TextureH));
		tess.addVertexWithUV(9 * Pixel, 1, 1 - 6 * Pixel, 24 * (1 / TextureW),
				0);
		tess.addVertexWithUV(10 * Pixel, 1, 1 - 6 * Pixel, 22 * (1 / TextureW),
				0);
		tess.addVertexWithUV(10 * Pixel, 1, 6 * Pixel, 22 * (1 / TextureW),
				(1 / TextureH));

		tess.addVertexWithUV(7 * Pixel, 1, 6 * Pixel, 24 * (1 / TextureW),
				(1 / TextureH));
		tess.addVertexWithUV(7 * Pixel, 1, 7 * Pixel, 24 * (1 / TextureW), 0);
		tess.addVertexWithUV(9 * Pixel, 1, 7 * Pixel, 22 * (1 / TextureW), 0);
		tess.addVertexWithUV(9 * Pixel, 1, 6 * Pixel, 22 * (1 / TextureW),
				(1 / TextureH));

		tess.addVertexWithUV(9 * Pixel, 1, 10 * Pixel, 24 * (1 / TextureW),
				(1 / TextureH));
		tess.addVertexWithUV(9 * Pixel, 1, 9 * Pixel, 24 * (1 / TextureW), 0);
		tess.addVertexWithUV(7 * Pixel, 1, 9 * Pixel, 22 * (1 / TextureW), 0);
		tess.addVertexWithUV(7 * Pixel, 1, 10 * Pixel, 22 * (1 / TextureW),
				(1 / TextureH));

		tess.addVertexWithUV(7 * Pixel, 0, 9 * Pixel, 27 * (1 / TextureW),
				16 * (1 / TextureH));// tube inside
		tess.addVertexWithUV(7 * Pixel, 1, 9 * Pixel, 27 * (1 / TextureW), 0);
		tess.addVertexWithUV(9 * Pixel, 1, 9 * Pixel, 25 * (1 / TextureW), 0);
		tess.addVertexWithUV(9 * Pixel, 0, 9 * Pixel, 25 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.addVertexWithUV(9 * Pixel, 0, 7 * Pixel, 27 * (1 / TextureW),
				16 * (1 / TextureH));
		tess.addVertexWithUV(9 * Pixel, 1, 7 * Pixel, 27 * (1 / TextureW), 0);
		tess.addVertexWithUV(7 * Pixel, 1, 7 * Pixel, 25 * (1 / TextureW), 0);
		tess.addVertexWithUV(7 * Pixel, 0, 7 * Pixel, 25 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.addVertexWithUV(9 * Pixel, 0, 9 * Pixel, 27 * (1 / TextureW),
				(1 / TextureH));
		tess.addVertexWithUV(9 * Pixel, 1, 9 * Pixel, 27 * (1 / TextureW), 0);
		tess.addVertexWithUV(9 * Pixel, 1, 7 * Pixel, 25 * (1 / TextureW), 0);
		tess.addVertexWithUV(9 * Pixel, 0, 7 * Pixel, 25 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.addVertexWithUV(7 * Pixel, 0, 7 * Pixel, 27 * (1 / TextureW),
				(1 / TextureH));
		tess.addVertexWithUV(7 * Pixel, 1, 7 * Pixel, 27 * (1 / TextureW), 0);
		tess.addVertexWithUV(7 * Pixel, 1, 9 * Pixel, 25 * (1 / TextureW), 0);
		tess.addVertexWithUV(7 * Pixel, 0, 9 * Pixel, 25 * (1 / TextureW),
				16 * (1 / TextureH));

		tess.draw();// teslator end
		GL11.glPopMatrix();
	}

}
