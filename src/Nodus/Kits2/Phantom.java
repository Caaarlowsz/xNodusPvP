package Nodus.Kits2;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Phantom implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;
	public static HashMap<String, Long> cooldown1;

	static {
		Phantom.cooldown = new HashMap<String, Long>();
		Phantom.cooldown1 = new HashMap<String, Long>();
	}

	public Phantom(final Main main) {
		Phantom.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("phantom")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.phantom")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Phantom");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.phantom.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					Habilidade.setAbility(p, "Phantom");
					espadameta.setDisplayName("§cSword");
					final ItemStack iron = new ItemStack(Material.FEATHER);
					final ItemMeta ironm = iron.getItemMeta();
					ironm.setDisplayName("§aI Believe a can Fly!");
					iron.setItemMeta(ironm);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { iron });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Phantom.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Phantom.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void voar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (e.getAction().name().contains("RIGHT") && p.getItemInHand().getType() == Material.FEATHER) {
			e.setCancelled(true);
			p.updateInventory();
			if (Main.phantom.contains(p.getName()) && (!Phantom.cooldown1.containsKey(p.getName())
					|| Phantom.cooldown1.get(p.getName()) <= System.currentTimeMillis())) {
				p.setAllowFlight(true);
				p.setFlying(true);
				p.sendMessage(ChatColor.BLUE + "Agora voce pode voar por 5 segundos!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
					@Override
					public void run() {
						p.setFlying(false);
						p.setAllowFlight(false);
						p.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ZOMBIE_UNFECT, 1.0f, 1.0f);
						p.sendMessage(ChatColor.RED + ";-; seu fly acabou");
					}
				}, 100L);
				Phantom.cooldown1.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L));
			}
		}
	}
}
