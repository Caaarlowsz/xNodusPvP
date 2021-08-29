package Nodus.Event;

import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Main.Habilidade;
import Nodus.Main.Main;

public class CompassListener implements Listener {
	public static Main plugin;

	public CompassListener(final Main main) {
		CompassListener.plugin = main;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(final PlayerInteractEvent event) {
		final ItemStack iron = new ItemStack(Material.COMPASS);
		final ItemMeta ironm = iron.getItemMeta();
		ironm.setDisplayName(ChatColor.RED + " ");
		iron.setItemMeta(ironm);
		final Player p = event.getPlayer();
		if (p.getItemInHand().getType() == Material.COMPASS & Main.COMPASS) {
			Boolean found = false;
			for (int i = 0; i < 1000; ++i) {
				final List<Entity> entities = (List<Entity>) p.getNearbyEntities((double) i, 128.0, (double) i);
				final Iterator<Entity> iterator = entities.iterator();
				if (iterator.hasNext()) {
					final Entity e = iterator.next();
					if (!e.getType().equals((Object) EntityType.PLAYER)
							|| e.getLocation().distance(p.getLocation()) < 1.0) {
						return;
					}
					p.setCompassTarget(e.getLocation());
					p.sendMessage("§7Apontando para a Localizacao de: §a§o" + ((Player) e).getName() + " §7( "
							+ Habilidade.getAbility((Player) e) + " §7)");
					found = true;
				}
				if (found) {
					break;
				}
			}
			if (!found) {
				p.sendMessage("§4Ninguem encontrado");
				p.setCompassTarget(new Location(p.getWorld(), 0.0, 60.0, 0.0));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove(final PlayerMoveEvent event) {
		final Player p = event.getPlayer();
		if (!Main.COMPASS || !Main.AUTO_COMPASS) {
			return;
		}
		Boolean found = false;
		for (int i = 0; i < 300; ++i) {
			final List<Entity> entities = (List<Entity>) p.getNearbyEntities((double) i, 64.0, (double) i);
			for (final Entity e : entities) {
				if (e.getType().equals((Object) EntityType.PLAYER)) {
					p.setCompassTarget(e.getLocation());
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}
		if (!found) {
			p.setCompassTarget(new Location(p.getWorld(), 0.0, 60.0, 0.0));
		}
	}
}
