package Nodus.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

public class Viking implements Listener, CommandExecutor {
	public static Main plugin;

	public Viking(final Main main) {
		Viking.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("viking")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.viking")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Viking");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.viking.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.WOOD_AXE);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§7Machadinha OP");
					Habilidade.setAbility(p, "Viking");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Viking.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Viking.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void main(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player d = (Player) e.getDamager();
			if (Main.viking.contains(d.getName()) && (d.getItemInHand().getType() == Material.WOOD_AXE
					|| d.getItemInHand().getType() == Material.STONE_AXE
					|| p.getItemInHand().getType() == Material.GOLD_AXE
					|| p.getItemInHand().getType() == Material.IRON_AXE
					|| p.getItemInHand().getType() == Material.DIAMOND_AXE)) {
				e.setDamage(e.getDamage() * 2.5);
			}
		}
	}
}
