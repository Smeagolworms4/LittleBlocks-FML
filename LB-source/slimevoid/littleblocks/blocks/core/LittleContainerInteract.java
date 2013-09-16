package slimevoid.littleblocks.blocks.core;

import net.minecraftforge.common.FakePlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

import slimevoid.littleblocks.core.lib.CoreLib;

public class LittleContainerInteract {
	
	@ForgeSubscribe
	public void onInteractEvent(PlayerOpenContainerEvent event) {
		FakePlayer fakePlayer = new FakePlayer(event.entityPlayer.worldObj, CoreLib.MOD_CHANNEL);
		fakePlayer.posX = event.entityPlayer.posX * 8;
		fakePlayer.posY = (event.entityPlayer.posY + (event.entityPlayer.height * 0.9)) * 8;
		fakePlayer.posZ = event.entityPlayer.posZ * 8;
		fakePlayer.openContainer = event.entityPlayer.openContainer;
		if (fakePlayer.openContainer.canInteractWith(fakePlayer)) {
			event.allowInteraction();
		}
	}
}

/*package slimevoid.littleblocks.blocks.core;

import slimevoid.littleblocks.api.ILBCommonProxy;
import slimevoid.littleblocks.world.LittleWorld;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.world.World;
import static net.minecraftforge.event.Event.Result.*;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ContainerInteractEvent;

public class LittleContainerInteract {
	
	@ForgeSubscribe
	public void onInteractEvent(ContainerInteractEvent event) {
		World littleWorld = (World) ((ILBCommonProxy)LBInit.LBM.getProxy()).getLittleWorld(event.entityPlayer.worldObj, false);
		if (event.container instanceof ContainerChest) {
			IInventory chest = ((ContainerChest)event.container).getLowerChestInventory();
			if (chest instanceof TileEntityChest) {
				TileEntityChest chestentity = (TileEntityChest)chest;
				if (LittleBlockDataHandler.isUseableByPlayer(chestentity, event.entityPlayer)) {
					event.setResult(ALLOW);
				}
			}
		}
		if (event.container instanceof ContainerDispenser) {
			TileEntityDispenser dispenser = ((ContainerDispenser)event.container).getDispenser();
			if (dispenser.getWorldObj() == littleWorld) {
				if (LittleBlockDataHandler.isUseableByPlayer(dispenser, event.entityPlayer)) {
					event.setResult(ALLOW);
				}
			}
		}
		if (event.container instanceof ContainerWorkbench) {
			ContainerWorkbench workbench = ((ContainerWorkbench)event.container);
			World worldobj = ((ContainerWorkbench)event.container).getWorldObj();
			if (worldobj == event.entityPlayer.worldObj) {
				boolean flag = LittleBlockDataHandler.isUseableByPlayer(
						event.entityPlayer,
						littleWorld,
						Block.workbench.blockID,
						workbench.getPosX(),
						workbench.getPosY(),
						workbench.getPosZ());
				if (flag) {
					event.setResult(Result.ALLOW);
				} else {
					event.setResult(Result.DEFAULT); 
				}
			}
		}
	}
	
}
*/