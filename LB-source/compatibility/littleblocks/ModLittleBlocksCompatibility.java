package compatibility.littleblocks;

import slimevoid.littleblocks.core.lib.CoreLib;
import slimevoid.littleblocks.tileentities.TileEntityLittleChunk;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "LittleBlocksCompatibility", name = "LittleBlocks - Compatibility", dependencies = "required-after:"+CoreLib.MOD_ID, version = "2.2.X", acceptedMinecraftVersions = "1.6.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class ModLittleBlocksCompatibility {
	
	
	@Instance("ModLittleBlocksCompatibility")
	public static ModLittleBlocksCompatibility instance;
	
	@EventHandler
	public void load(FMLInitializationEvent event) throws Exception {
		GameRegistry.registerTileEntity(TileEntityLittleChunk.class, "littleBlocks");
	}
	
}