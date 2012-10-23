package littleblocks.proxy;

import littleblocks.core.LBCore;
import littleblocks.network.ClientPacketHandler;
import littleblocks.network.LBPacketIds;
import littleblocks.network.packets.PacketLittleBlocksSettings;
import littleblocks.render.BlockLittleBlocksRenderer;
import littleblocks.render.TileEntityLittleBlocksRenderer;
import littleblocks.tickhandlers.ClientTickHandler;
import littleblocks.world.LittleWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.TickRegistry;
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public String getMinecraftDir() {
		return Minecraft.getMinecraftDir().toString();
	}

	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerBlockHandler(new BlockLittleBlocksRenderer());
	}

	@Override
	public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {
		ClientRegistry.bindTileEntitySpecialRenderer(
				clazz,
				new TileEntityLittleBlocksRenderer());
	}

	@Override
	public void displayTileEntityGui(EntityPlayer entityplayer, TileEntity tileentity) {
	}

	private static Minecraft mc = ModLoader.getMinecraftInstance();

	@Override
	public void registerTickHandler() {
		LBCore.littleDimensionClient = -1;
		LBCore.littleProviderTypeClient = -1;
		if (FMLCommonHandler.instance().getSide().isClient() && ModLoader.getMinecraftInstance().isSingleplayer()) {
			LBCore.littleDimensionServer = -1;
			LBCore.littleProviderTypeServer = -1;
		}
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}

	@Override
	public void login(NetHandler handler, NetworkManager manager, Packet1Login login) {
		World world = getWorld(handler);
		if (world != null) {
			PacketLittleBlocksSettings packet = new PacketLittleBlocksSettings();
			packet.setCommand(LBPacketIds.FETCH);
			ClientPacketHandler.sendPacket(packet.getPacket());
		}
	}

	@Override
	public LittleWorld getLittleWorld(World world, boolean needsRefresh) {
		if (world != null) {
			if (world.isRemote) {
				if (LBCore.littleDimensionClient == -1) {
					LBCore.littleDimensionClient = DimensionManager.getNextFreeDimId();
					LBCore.littleProviderTypeClient = DimensionManager.getProviderType(world.provider.dimensionId);
					DimensionManager.registerDimension(LBCore.littleDimensionClient, LBCore.littleProviderTypeClient);
					LBCore.littleProviderClient = DimensionManager.createProviderFor(LBCore.littleDimensionClient);
				}
				if (LBCore.littleWorldClient == null || LBCore.littleWorldClient
						.isOutdated(world) || needsRefresh) {
					
					LBCore.littleWorldClient = new LittleWorld(world, LBCore.littleProviderClient);
				}
				return LBCore.littleWorldClient;
			} else {
				return super.getLittleWorld(world, needsRefresh);
			}
		}
		return null;
	}

	@Override
	public World getWorld() {
		return ModLoader.getMinecraftInstance().theWorld;
	}

	@Override
	public EntityPlayer getPlayer() {
		return ModLoader.getMinecraftInstance().thePlayer;
	}

	public World getWorld(NetHandler handler) {
		if (handler instanceof NetClientHandler) {
			return ((NetClientHandler) handler).getPlayer().worldObj;
		}
		return null;
	}
}
