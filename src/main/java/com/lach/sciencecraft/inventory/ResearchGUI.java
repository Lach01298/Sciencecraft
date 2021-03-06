package com.lach.sciencecraft.inventory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.lach.sciencecraft.entitys.TileEntityBunsen;
import com.lach.sciencecraft.entitys.TileEntityScienceTable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.AchievementPage;

public class ResearchGUI extends GuiScreen implements IProgressMeter {
	private LinkedList<Achievement> minecraftAchievements = new LinkedList<Achievement>();
	private static final int _y = AchievementList.minDisplayColumn * 24 - 112;
	private static final int _z = AchievementList.minDisplayRow * 24 - 112;
	private static final int A = AchievementList.maxDisplayColumn * 24 - 77;
	private static final int B = AchievementList.maxDisplayRow * 24 - 77;
	protected int h;
	protected int _i;
	protected float r = 1.0F;
	protected double u;
	protected double v;
	protected double w;
	protected double _x;
	private int Thing;
	private boolean active = true;
	private int currentPage = -1;
	protected int X = 256;
	protected int Y = 202;
	protected GuiScreen screen;
	private static final ResourceLocation AchiveBackGround = new ResourceLocation(
			"textures/gui/achievement/achievement_background.png");
	public final ResourceLocation texture = new ResourceLocation(
			"sciencecraft", "/textures/gui/Reseach_backGround.png");
	private StatFileWriter statWriter;
	protected double s;
	protected double t;
	private GuiButton button;

	public ResearchGUI(GuiScreen GUIScreen, StatFileWriter stat) {
		this.screen = GUIScreen;
		this.statWriter = stat;
		short xLenght = 144;
		short yLenght = 144;
		this.s = this.u = this.w = (double) (AchievementList.openInventory.displayColumn
				* 24 - xLenght / 2 - 12);
		this.t = (double) (AchievementList.openInventory.displayRow * 24 - yLenght / 2);

	}

	public void initGui() {
		this.mc.getNetHandler().addToSendQueue(
				new C16PacketClientStatus(
						C16PacketClientStatus.EnumState.REQUEST_STATS));
		this.buttonList.clear();
		this.buttonList.add(new GuiOptionButton(1, this.width / 2 + 24,
				this.height / 2 + 74, 80, 20, I18n.format("gui.done",
						new Object[0])));
		this.buttonList
				.add(button = new GuiButton(2, (width - X) / 2 + 24,
						height / 2 + 74, 125, 20, AchievementPage
								.getTitle(currentPage)));
	}

	protected void actionPerformed(GuiButton p_146284_1_) {
		if (!this.active) {
			if (p_146284_1_.id == 1) {
				this.mc.displayGuiScreen(this.screen);
			}

			if (p_146284_1_.id == 2) {
				currentPage++;
				if (currentPage >= AchievementPage.getAchievementPages().size()) {
					currentPage = -1;
				}
				button.displayString = AchievementPage.getTitle(currentPage);
			}
		}
	}

	protected void keyTyped(char par1, int par2) {
		if (par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
		} else {
			super.keyTyped(par1, par2);
		}
	}

	public void drawScreen(int par1, int par2, float par3) {
		if (this.active) {
			this.drawDefaultBackground();
			this.drawCenteredString(this.fontRendererObj,
					I18n.format("multiplayer.downloadingStats", new Object[0]),
					this.width / 2, this.height / 2, 16777215);
			this.drawCenteredString(
					this.fontRendererObj,
					field_146510_b_[(int) (Minecraft.getSystemTime() / 150L % (long) field_146510_b_.length)],
					this.width / 2, this.height / 2
							+ this.fontRendererObj.FONT_HEIGHT * 2, 16777215);
		} else {
			int k;

			if (Mouse.isButtonDown(0)) {
				k = (this.width - this.X) / 2;
				int l = (this.height - this.Y) / 2;
				int i1 = k + 8;
				int j1 = l + 17;

				if ((this.Thing == 0 || this.Thing == 1) && par1 >= i1
						&& par1 < i1 + 224 && par2 >= j1 && par2 < j1 + 155) {
					if (this.Thing == 0) {
						this.Thing = 1;
					} else {
						this.u -= (double) ((float) (par1 - this.h) * this.r);
						this.v -= (double) ((float) (par2 - this._i) * this.r);
						this.w = this.s = this.u;
						this._x = this.t = this.v;
					}

					this.h = par1;
					this._i = par2;
				}
			} else {
				this.Thing = 0;
			}

			k = Mouse.getDWheel();
			float f4 = this.r;

			if (k < 0) {
				this.r += 0.25F;
			} else if (k > 0) {
				this.r -= 0.25F;
			}

			this.r = MathHelper.clamp_float(this.r, 1.0F, 2.0F);

			if (this.r != f4) {
				float f6 = f4 - this.r;
				float f5 = f4 * (float) this.X;
				float f1 = f4 * (float) this.Y;
				float f2 = this.r * (float) this.X;
				float f3 = this.r * (float) this.Y;
				this.u -= (double) ((f2 - f5) * 0.5F);
				this.v -= (double) ((f3 - f1) * 0.5F);
				this.w = this.s = this.u;
				this._x = this.t = this.v;
			}

			if (this.w < (double) _y) {
				this.w = (double) _y;
			}

			if (this._x < (double) _z) {
				this._x = (double) _z;
			}

			if (this.w >= (double) A) {
				this.w = (double) (A - 1);
			}

			if (this._x >= (double) B) {
				this._x = (double) (B - 1);
			}

			this.drawDefaultBackground();
			this.RenderThing(par1, par2, par3);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			this.RenderOtherThing();
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
	}

	public void func_146509_g() {
		if (this.active) {
			this.active = false;
		}

	}

	protected void RenderThing(int x, int y, float F1) {
		int k = MathHelper.floor_double(this.s + (this.u - this.s)
				* (double) F1);
		int l = MathHelper.floor_double(this.t + (this.v - this.t)
				* (double) F1);

		if (k < _y) {
			k = _y;
		}

		if (l < _z) {
			l = _z;
		}

		if (k >= A) {
			k = A - 1;
		}

		if (l >= B) {
			l = B - 1;
		}

		int i1 = (this.width - this.X) / 2;
		int j1 = (this.height - this.Y) / 2;
		int k1 = i1 + 16;
		int l1 = j1 + 17;
		this.zLevel = 0.0F;
		GL11.glDepthFunc(GL11.GL_GEQUAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) k1, (float) l1, -200.0F);
		GL11.glScalef(1.0F / this.r, 1.0F / this.r, 0.0F);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		int i2 = k + 288 >> 4;
		int j2 = l + 288 >> 4;
		int k2 = (k + 288) % 16;
		int l2 = (l + 288) % 16;
		boolean flag = true;
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		boolean flag4 = true;
		Random random = new Random();
		float f1 = 16.0F / this.r;
		float f2 = 16.0F / this.r;
		int i3;
		int j3;
		int k3;

		for (i3 = 0; (float) i3 * f1 - (float) l2 < 155.0F; ++i3) {
			float f3 = 0.6F - (float) (j2 + i3) / 25.0F * 0.3F;
			GL11.glColor4f(f3, f3, f3, 1.0F);

			for (j3 = 0; (float) j3 * f2 - (float) k2 < 224.0F; ++j3) {
				random.setSeed((long) (this.mc.getSession().getPlayerID()
						.hashCode()
						+ i2 + j3 + (j2 + i3) * 16));
				k3 = random.nextInt(1 + j2 + i3) + (j2 + i3) / 2;
				IIcon iicon = Blocks.sand.getIcon(0, 0);

				if (k3 <= 37 && j2 + i3 != 35) {
					if (k3 == 22) {
						if (random.nextInt(2) == 0) {
							iicon = Blocks.diamond_ore.getIcon(0, 0);
						} else {
							iicon = Blocks.redstone_ore.getIcon(0, 0);
						}
					} else if (k3 == 10) {
						iicon = Blocks.iron_ore.getIcon(0, 0);
					} else if (k3 == 8) {
						iicon = Blocks.coal_ore.getIcon(0, 0);
					} else if (k3 > 4) {
						iicon = Blocks.stone.getIcon(0, 0);
					} else if (k3 > 0) {
						iicon = Blocks.dirt.getIcon(0, 0);
					}
				} else {
					iicon = Blocks.bedrock.getIcon(0, 0);
				}

				this.mc.getTextureManager().bindTexture(
						TextureMap.locationBlocksTexture);
				this.drawTexturedModelRectFromIcon(j3 * 16 - k2, i3 * 16 - l2,
						iicon, 16, 16);
			}
		}

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		this.mc.getTextureManager().bindTexture(AchiveBackGround);
		int i4;
		int j4;
		int l4;

		List<Achievement> achievementList = (currentPage == -1 ? minecraftAchievements
				: AchievementPage.getAchievementPage(currentPage)
						.getAchievements());
		for (i3 = 0; i3 < achievementList.size(); ++i3) {
			Achievement achievement1 = achievementList.get(i3);

			if (achievement1.parentAchievement != null
					&& achievementList.contains(achievement1.parentAchievement)) {
				j3 = achievement1.displayColumn * 24 - k + 11;
				k3 = achievement1.displayRow * 24 - l + 11;
				l4 = achievement1.parentAchievement.displayColumn * 24 - k + 11;
				int l3 = achievement1.parentAchievement.displayRow * 24 - l
						+ 11;
				boolean flag5 = this.statWriter
						.hasAchievementUnlocked(achievement1);
				boolean flag6 = this.statWriter
						.canUnlockAchievement(achievement1);
				i4 = this.statWriter.func_150874_c(achievement1);

				if (i4 <= 4) {
					j4 = -16777216;

					if (flag5) {
						j4 = -6250336;
					} else if (flag6) {
						j4 = -16711936;
					}

					this.drawHorizontalLine(j3, l4, k3, j4);
					this.drawVerticalLine(l4, k3, l3, j4);

					if (j3 > l4) {
						this.drawTexturedModalRect(j3 - 11 - 7, k3 - 5, 114,
								234, 7, 11);
					} else if (j3 < l4) {
						this.drawTexturedModalRect(j3 + 11, k3 - 5, 107, 234,
								7, 11);
					} else if (k3 > l3) {
						this.drawTexturedModalRect(j3 - 5, k3 - 11 - 7, 96,
								234, 11, 7);
					} else if (k3 < l3) {
						this.drawTexturedModalRect(j3 - 5, k3 + 11, 96, 241,
								11, 7);
					}
				}
			}
		}

		Achievement achievement = null;
		RenderItem renderitem = new RenderItem();
		float f4 = (float) (x - k1) * this.r;
		float f5 = (float) (y - l1) * this.r;
		RenderHelper.enableGUIStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		int i5;
		int j5;

		for (l4 = 0; l4 < achievementList.size(); ++l4) {
			Achievement achievement2 = (Achievement) achievementList.get(l4);
			i5 = achievement2.displayColumn * 24 - k;
			j5 = achievement2.displayRow * 24 - l;

			if (i5 >= -24 && j5 >= -24 && (float) i5 <= 224.0F * this.r
					&& (float) j5 <= 155.0F * this.r) {
				i4 = this.statWriter.func_150874_c(achievement2);
				float f6;

				if (this.statWriter.hasAchievementUnlocked(achievement2)) {
					f6 = 0.75F;
					GL11.glColor4f(f6, f6, f6, 1.0F);
				} else if (this.statWriter.canUnlockAchievement(achievement2)) {
					f6 = 1.0F;
					GL11.glColor4f(f6, f6, f6, 1.0F);
				} else if (i4 < 3) {
					f6 = 0.3F;
					GL11.glColor4f(f6, f6, f6, 1.0F);
				} else if (i4 == 3) {
					f6 = 0.2F;
					GL11.glColor4f(f6, f6, f6, 1.0F);
				} else {
					if (i4 != 4) {
						continue;
					}

					f6 = 0.1F;
					GL11.glColor4f(f6, f6, f6, 1.0F);
				}

				this.mc.getTextureManager().bindTexture(AchiveBackGround);

				if (achievement2.getSpecial()) {
					this.drawTexturedModalRect(i5 - 2, j5 - 2, 26, 202, 26, 26);
				} else {
					this.drawTexturedModalRect(i5 - 2, j5 - 2, 0, 202, 26, 26);
				}

				if (!this.statWriter.canUnlockAchievement(achievement2)) {
					f6 = 0.1F;
					GL11.glColor4f(f6, f6, f6, 1.0F);
					renderitem.renderWithColor = false;
				}

				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_CULL_FACE);
				renderitem.renderItemAndEffectIntoGUI(this.mc.fontRenderer,
						this.mc.getTextureManager(), achievement2.theItemStack,
						i5 + 3, j5 + 3);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glDisable(GL11.GL_LIGHTING);

				if (!this.statWriter.canUnlockAchievement(achievement2)) {
					renderitem.renderWithColor = true;
				}

				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

				if (f4 >= (float) i5 && f4 <= (float) (i5 + 22)
						&& f5 >= (float) j5 && f5 <= (float) (j5 + 22)) {
					achievement = achievement2;
				}
			}
		}

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(AchiveBackGround);
		this.drawTexturedModalRect(i1, j1, 0, 0, this.X, this.Y);
		this.zLevel = 0.0F;
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		super.drawScreen(x, y, F1);

		if (achievement != null) {
			String s1 = achievement.func_150951_e().getUnformattedText();
			String s2 = achievement.getDescription();
			i5 = x + 12;
			j5 = y - 4;
			i4 = this.statWriter.func_150874_c(achievement);

			if (!this.statWriter.canUnlockAchievement(achievement)) {
				String s;
				int k4;

				if (i4 == 3) {
					s1 = I18n.format("achievement.unknown", new Object[0]);
					j4 = Math.max(this.fontRendererObj.getStringWidth(s1), 120);
					s = (new ChatComponentTranslation("achievement.requires",
							new Object[] { achievement.parentAchievement
									.func_150951_e() })).getUnformattedText();
					k4 = this.fontRendererObj.splitStringWidth(s, j4);
					this.drawGradientRect(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k4
							+ 12 + 3, -1073741824, -1073741824);
					this.fontRendererObj.drawSplitString(s, i5, j5 + 12, j4,
							-9416624);
				} else if (i4 < 3) {
					j4 = Math.max(this.fontRendererObj.getStringWidth(s1), 120);
					s = (new ChatComponentTranslation("achievement.requires",
							new Object[] { achievement.parentAchievement
									.func_150951_e() })).getUnformattedText();
					k4 = this.fontRendererObj.splitStringWidth(s, j4);
					this.drawGradientRect(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k4
							+ 12 + 3, -1073741824, -1073741824);
					this.fontRendererObj.drawSplitString(s, i5, j5 + 12, j4,
							-9416624);
				} else {
					s1 = null;
				}
			} else {
				j4 = Math.max(this.fontRendererObj.getStringWidth(s1), 120);
				int k5 = this.fontRendererObj.splitStringWidth(s2, j4);

				if (this.statWriter.hasAchievementUnlocked(achievement)) {
					k5 += 12;
				}

				this.drawGradientRect(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k5 + 3
						+ 12, -1073741824, -1073741824);
				this.fontRendererObj.drawSplitString(s2, i5, j5 + 12, j4,
						-6250336);

				if (this.statWriter.hasAchievementUnlocked(achievement)) {
					this.fontRendererObj.drawStringWithShadow(
							I18n.format("achievement.taken", new Object[0]),
							i5, j5 + k5 + 4, -7302913);
				}
			}

			if (s1 != null) {
				this.fontRendererObj
						.drawStringWithShadow(
								s1,
								i5,
								j5,
								this.statWriter
										.canUnlockAchievement(achievement) ? (achievement
										.getSpecial() ? -128 : -1)
										: (achievement.getSpecial() ? -8355776
												: -8355712));
			}
		}

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LIGHTING);
		RenderHelper.disableStandardItemLighting();
	}

	protected void RenderOtherThing() {
		int i = (this.width - this.X) / 2;
		int j = (this.height - this.Y) / 2;
		this.fontRendererObj.drawString("Achievements", i + 15, j + 5, 4210752);
	}

	public boolean doesGuiPauseGame() {
		return true;
	}
}
