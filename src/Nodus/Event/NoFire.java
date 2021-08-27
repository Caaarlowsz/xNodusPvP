package Nodus.Event;

import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.event.*;

public class NoFire implements Listener
{
    @EventHandler
    public void NaoQueimar(final BlockIgniteEvent evento) {
        evento.setCancelled(true);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProtectInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (event.getAction() == Action.LEFT_CLICK_BLOCK && block.getRelative(BlockFace.UP).getType() == Material.FIRE) {
            event.setCancelled(true);
        }
    }
}
