package com.lach.sciencecraft.proxy;

import com.lach.sciencecraft.entitys.TileEntityBunsen;
import com.lach.sciencecraft.proxy.CommonProxy;
import com.lach.sciencecraft.render.RenderBusen;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBunsen.class,
				new RenderBusen());

	}

}