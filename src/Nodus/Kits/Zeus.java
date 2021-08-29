package Nodus.Kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Zeus implements Listener, CommandExecutor {
	public static Main plugin;
	public static ArrayList<Player> Cooldown;
	public static HashMap<String, ItemStack[]> s;

	static {
		Zeus.Cooldown = new ArrayList<Player>();
		Zeus.s = new HashMap<String, ItemStack[]>();
	}

	public Zeus(final Main main) {
		Zeus.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Zeus")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Zeus")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Zeus");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Zeus.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Zeus");
					p.getInventory().addItem(new ItemStack[] { espada });
					final ItemStack bow = new ItemStack(Material.BOW);
					final ItemMeta bowmeta = bow.getItemMeta();
					bowmeta.setDisplayName("§bZeus");
					bow.setItemMeta(bowmeta);
					final ItemStack bow2 = new ItemStack(Material.ARROW, 5);
					p.getInventory().setItem(1, bow);
					p.getInventory().setItem(15, bow2);
					Main.giveSoup(p, 33);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Zeus.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Zeus.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerStomp1(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (e.getCause() != EntityDamageEvent.DamageCause.LIGHTNING) {
			return;
		}
		if (Main.Zeus.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEnderArcher(final ProjectileHitEvent e) {
		if (e.getEntity() instanceof Arrow) {
			final Arrow a = (Arrow) e.getEntity();
			if (a.getShooter() instanceof Player) {
				final Player p = (Player) a.getShooter();
				if (!Zeus.Cooldown.contains(p.getPlayer()) && Main.Zeus.contains(p.getName())) {
					final Location loc = a.getLocation();
					a.getLocation().getWorld().strikeLightning(loc);
					a.remove();
					if (Zeus.Cooldown.contains(p)) {
						p.sendMessage("§bAcalme-se");
					}
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							Zeus.Cooldown.remove(p.getPlayer());
						}
					}, 30L);
				}
			}
		}
	}
}
