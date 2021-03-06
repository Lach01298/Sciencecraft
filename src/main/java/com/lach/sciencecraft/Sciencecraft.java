package com.lach.sciencecraft;

import com.lach.sciencecraft.complexBlock.SciencecraftGUIHandler;
import com.lach.sciencecraft.entitys.RegisteryEntitys;
import com.lach.sciencecraft.proxy.CommonProxy;
import com.lach.sciencecraft.recipes.CraftingRecipes;
import com.lach.sciencecraft.recipes.SmeltingRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "sciencecraft", name = "Sciencecraft", version = Sciencecraft.VERSION, acceptedMinecraftVersions = Sciencecraft.MCVERSION)
public class Sciencecraft {

	// The instance of your mod that Forge uses.
	@Instance("sciencecraft")
	public static Sciencecraft instance;

	public static final String VERSION = "(1.7.10)-0.0.5";
	public static final String MCVERSION = "1.7.10";

	// world gen
	WorldEventManager WorldGen = new WorldEventManager();

	// creative tabs
	public static CreativeTabs SciencecraftWorldgen = new CreativeTabs(
			"SciencecraftWorldGen") {

		public Item getTabIconItem() {
			return Item.getItemFromBlock(CopperOre);
		}
	};

	public static CreativeTabs Sciencecraft = new CreativeTabs("Sciencecraft") {

		public Item getTabIconItem() {

			return Match;
		}
	};

	// constants
	private final int Woodlevel = 0;
	private final int Stonelevel = 1;
	private final int Ironlevel = 2;
	private final int Diamondlevel = 3;
	private final String[] phosphrousTypes = { "red", "white", "black" };
	private final String[] plateTypes = { "Iron", "Steel", "Lead", "Copper",
			"Tin" };

	public static final int Bunsen_ID = 0;
	public static final int ScienceTable_ID = 1;

	// ingots
	public final static Item ItemCopper = new ItemIngot().setTextureName("sciencecraft:copper ingot").setUnlocalizedName("copper");
	public final static Item ItemTin = new ItemIngot().setTextureName("sciencecraft:tin ingot").setUnlocalizedName("tin");;
	public final static Item ItemLead = new ItemIngot().setTextureName("sciencecraft:lead ingot").setUnlocalizedName("lead");
	public final static Item ItemZinc = new ItemIngot().setTextureName("sciencecraft:zinc ingot").setUnlocalizedName("zinc");
	public final static Item ItemTiatanium = new ItemIngot().setTextureName("sciencecraft:titanium ingot").setUnlocalizedName("tiatanium");
	public final static Item ItemAluminium = new ItemIngot().setTextureName("sciencecraft:aluminium ingot").setUnlocalizedName("aluminium");
	public final static Item ItemSilver = new ItemIngot().setTextureName("sciencecraft:silver ingot").setUnlocalizedName("silver");

	// items
	public final static Item ItemPhosphrousRed = new ItemPhosphrous().setUnlocalizedName("phousphourusRed").setTextureName("sciencecraft:red phosphorus");
	public final static Item ItemPhosphrousBlack = new ItemPhosphrous().setUnlocalizedName("phousphourusBlack").setTextureName("sciencecraft:black phosphorus");
	public final static Item ItemPhosphrousWhite = new ItemPhosphrous().setUnlocalizedName("phousphourusWhite").setTextureName("sciencecraft:white phosphorus");
	public final static Item ItemGraphite = new ItemGraphite().setTextureName("graphite").setUnlocalizedName("graphite");

	public final static Item CopperPlate = new ItemPlate().setTextureName("sciencecraft:Copper plate").setUnlocalizedName("copperPlate");
	public final static Item TinPlate = new ItemPlate().setTextureName("sciencecraft:Tin plate").setUnlocalizedName("tinPlate");
	public final static Item IronPlate = new ItemPlate().setTextureName("sciencecraft:Iron plate").setUnlocalizedName("ironPlate");
	public final static Item LeadPlate = new ItemPlate().setTextureName("sciencecraft:Lead plate").setUnlocalizedName("leadPlate");
	public final static Item SteelPlate = new ItemPlate().setTextureName("sciencecraft:Steel plate").setUnlocalizedName("steelPlate");

	public final static Item Notes = new ItemNotes().setTextureName("sciencecraft:notes").setUnlocalizedName("notes");

	// items with abilitys
	public final static Item Match = new ItemMatch()
			.setTextureName("sciencecraft:Match");
	// public final static Item Research = new
	// ItemResearch().setTextureName("sciencecraft:notes");

	// ores basic ores
	public final static Block CopperOre = new BasicOre().setBlockTextureName("sciencecraft:CopperOre").setBlockName("copperOre");
	public final static Block TinOre = new BasicOre().setBlockTextureName("sciencecraft:TinOre").setBlockName("tinOre");
	public final static Block LeadOre = new BasicOre().setBlockTextureName("sciencecraft:lead ore").setBlockName("leadOre");
	public final static Block ZincOre = new BasicOre().setBlockTextureName("sciencecraft:Zinc ore").setBlockName("zincOre");
	public final static Block SilverOre = new BasicOre().setBlockTextureName("sciencecraft:silver ore").setBlockName("silverOre");
	public final static Block PhousphorusOre = new BlockPhosphorusOre().setBlockTextureName("sciencecraft:phousporus ore").setBlockName("phousphorusOre");
	public final static Block GraphiteOre = new BlockGraphiteOre().setBlockTextureName("sciencecraft:graphite ore").setBlockName("graphiteOre");

	// hard ores
	public final static Block TiataniumOre = new HardOre().setBlockTextureName("sciencecraft:titanium ore").setBlockName("tiataniumOre");
	public final static Block AluminiumOre = new HardOre().setBlockTextureName("sciencecraft:aluminium ore").setBlockName("aluminiumOre");
	public final static Block UraniumOre = new HardOre().setBlockTextureName("sciencecraft:uranium ore").setBlockName("uraniumOre");

	// blocks

	public final static Block BlastFurnaceBrick = new BlockBlastFurnaceBrick(467, Material.rock).setBlockTextureName("sciencecraft:blast furnace brick").setBlockName("blastFurnaceBrick");
	public final static Block SciencecraftingTable = new BlockSciencecraftingTable().setBlockTextureName("sciencecraft:ScienceTable").setBlockName("scienceTable");
	public final static Block BlockBunsen = new BlockBunsen(Material.iron).setBlockName("Bunsen");

	// gases
	// public final static Block BlockHydrogen = new BlockHydrogenGas(501);
	// public final static Block BlockChlorine = new BlockChlorineGas(506);

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "com.lach.sciencecraft.proxy.ClientProxy", serverSide = "com.lach.sciencecraft.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this,new SciencecraftGUIHandler());
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		proxy.registerProxies();

		// ores

		GameRegistry.registerBlock(CopperOre, "CopperOre");
		OreDictionary.registerOre("OreCopper", CopperOre);

		GameRegistry.registerBlock(TinOre, "TinOre");
		OreDictionary.registerOre("OreTinr", TinOre);

		GameRegistry.registerBlock(LeadOre, "LeadOre");
		OreDictionary.registerOre("Orelead", LeadOre);

		GameRegistry.registerBlock(ZincOre, "ZincOre");
		OreDictionary.registerOre("OreZinc", ZincOre);

		GameRegistry.registerBlock(TiataniumOre, "TiataniumOre");

		GameRegistry.registerBlock(AluminiumOre, "AluminiumOre");

		GameRegistry.registerBlock(SilverOre, "SilverOre");

		GameRegistry.registerBlock(PhousphorusOre, "PhousphorusOre");

		GameRegistry.registerBlock(UraniumOre, "UraniumOre");

		GameRegistry.registerBlock(GraphiteOre, "GraphiteOre");

		// blocks

		GameRegistry.registerBlock(SciencecraftingTable, "ScienceTable");

		GameRegistry.registerBlock(BlastFurnaceBrick, "BlastFurnaceBrick");

		GameRegistry.registerBlock(BlockBunsen, "BlockBunsen");

		// gases
		// LanguageRegistry.addName(BlockHydrogen, "Hydrogen");
		// GameRegistry.registerBlock(BlockHydrogen, "Hydrogen");

		// LanguageRegistry.addName(BlockChlorine, "Chlorine");
		// GameRegistry.registerBlock(BlockChlorine, "Chlorine");

		// ingots

		OreDictionary.registerOre("IngotCopper", ItemCopper);
		GameRegistry.registerItem(ItemCopper, "Copper");

		OreDictionary.registerOre("IngotTin", ItemTin);
		GameRegistry.registerItem(ItemTin, "Tin");

		OreDictionary.registerOre("IngotLead", ItemLead);
		GameRegistry.registerItem(ItemLead, "Lead");

		OreDictionary.registerOre("IngotZinc", ItemZinc);
		GameRegistry.registerItem(ItemZinc, "zinc");

		OreDictionary.registerOre("IngotTiatanium", ItemTiatanium);
		GameRegistry.registerItem(ItemTiatanium, "Tiatanium");

		OreDictionary.registerOre("IngotAluminium", ItemAluminium);
		GameRegistry.registerItem(ItemAluminium, "Aluminium");

		OreDictionary.registerOre("IngotSilver", ItemSilver);
		GameRegistry.registerItem(ItemSilver, "Silver");

		// other world items
		OreDictionary.registerOre("PhosphrousRed", ItemPhosphrousRed);
		GameRegistry.registerItem(ItemPhosphrousRed, "PhosphrousRed");
		OreDictionary.registerOre("PhosphrousBlack", ItemPhosphrousBlack);
		GameRegistry.registerItem(ItemPhosphrousBlack, "PhosphrousBlack");
		OreDictionary.registerOre("PhosphrousWhite", ItemPhosphrousWhite);
		GameRegistry.registerItem(ItemPhosphrousWhite, "PhosphrousWhite");

		OreDictionary.registerOre("ItemGraphite", ItemGraphite);
		GameRegistry.registerItem(ItemGraphite, "ItemGraphite");

		// items
		GameRegistry.registerItem(CopperPlate, "CopperPlate");
		GameRegistry.registerItem(TinPlate, "TinPlate");
		GameRegistry.registerItem(LeadPlate, "LeadPlate");
		GameRegistry.registerItem(IronPlate, "IronPlate");
		GameRegistry.registerItem(SteelPlate, "SteelPlate");
		GameRegistry.registerItem(Match, "Match");
		GameRegistry.registerItem(Notes, "Notes");
		// GameRegistry.registerItem(Research, "Research");

		// recipes
		SmeltingRecipes.loadRecipes();
		CraftingRecipes.loadRecipes();
		RegisteryEntitys.loadTileEntitys();
		RegisteryEntitys.loadEntitys();

		// world gen
		GameRegistry.registerWorldGenerator(WorldGen, 0);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}