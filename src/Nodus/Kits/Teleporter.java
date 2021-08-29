package Nodus.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Teleporter implements Listener, CommandExecutor {
	public static Main plugin;

	public Teleporter(final Main main) {
		Teleporter.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("teleporter")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.teleporter")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Teleporter");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.teleporter.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					p.getInventory().addItem(new ItemStack[] { espada });
					final ItemStack bow = new ItemStack(Material.BOW);
					final ItemMeta bowmeta = bow.getItemMeta();
					bowmeta.setDisplayName("§5Arco dus teleport");
					Habilidade.setAbility(p, "Teleporter");
					bow.setItemMeta(bowmeta);
					final ItemStack bow2 = new ItemStack(Material.ARROW, 15);
					p.getInventory().setItem(1, bow);
					p.getInventory().setItem(2, bow2);
					Main.giveSoup(p, 32);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Teleporter.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Teleporter.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onEnderArcher(final ProjectileHitEvent e) {
		if (e.getEntity() instanceof Arrow) {
			final Arrow a = (Arrow) e.getEntity();
			if (a.getShooter() instanceof Player) {
				final Player p = (Player) a.getShooter();
				if (Main.teleporter.contains(p.getName())) {
					final Location loc = a.getLocation();
					p.teleport(loc);
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5.0f, 1.0f);
					p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
					p.sendMessage("§5Teleportado!");
					a.remove();
				}
			}
		}
	}
}
