package Nodus.Event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoFire implements Listener {
	@EventHandler
	public void NaoQueimar(final BlockIgniteEvent evento) {
		evento.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onProtectInteract(final PlayerInteractEvent event) {
		final Block block = event.getClickedBlock();
		if (block == null) {
			return;
		}
		if (event.getAction() == Action.LEFT_CLICK_BLOCK
				&& block.getRelative(BlockFace.UP).getType() == Material.FIRE) {
			event.setCancelled(true);
		}
	}
}
