package Nodus.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Bomber implements Listener, CommandExecutor {
	public static Main plugin;
	public int DanoBoxer;

	public Bomber(final Main main) {
		this.DanoBoxer = 5;
		Bomber.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("boxer")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.boxer")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Boxer");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.bomber.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.QUARTZ);
					final ItemMeta espadameta = espada.getItemMeta();
					Habilidade.setAbility(p, "Boxer");
					espadameta.setDisplayName("§cSword");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Bomber.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Bomber.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	public void onEntityDamage(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			final Player boxer = (Player) e.getDamager();
			if (Main.bomber.contains(boxer.getName()) && boxer.getItemInHand().getType() == Material.QUARTZ) {
				e.setDamage(this.DanoBoxer);
			}
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player boxer = (Player) e.getEntity();
			final Player player = (Player) e.getDamager();
			if (Main.bomber.contains(boxer.getName()) && player.getItemInHand() == null) {
				e.setDamage(e.getDamage() - 1.0);
			}
		}
	}
}
