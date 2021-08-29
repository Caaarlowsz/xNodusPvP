package Nodus.Kits2;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Achilles implements Listener, CommandExecutor {
	public static Main plugin;

	public Achilles(final Main main) {
		Achilles.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Achilles")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Achilles")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Achilles");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.achilles.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					Habilidade.setAbility(p, "Achilles");
					espadameta.setDisplayName("§cSword");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Achilles.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Achilles.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onDamageAchilles(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player) {
			final Player damager = (Player) e.getDamager();
			if (Main.achilles.contains(damager.getName()) && damager.getItemInHand() != null) {
				if (damager.getItemInHand().getType() == Material.GOLD_SWORD) {
					e.setDamage(1.0);
				} else if (damager.getItemInHand().getType() == Material.STONE_SWORD) {
					e.setDamage(2.0);
				} else if (damager.getItemInHand().getType() == Material.IRON_SWORD) {
					e.setDamage(3.0);
				} else if (damager.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					e.setDamage(4.0);
				} else if (damager.getItemInHand().getType() == Material.WOOD_SWORD) {
					e.setDamage(7.0);
				}
			}
		}
	}
}
