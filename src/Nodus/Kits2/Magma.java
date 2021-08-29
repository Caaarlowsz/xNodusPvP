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

public class Magma implements Listener, CommandExecutor {
	public static Main plugin;

	public Magma(final Main main) {
		Magma.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Magma")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Magma")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Magma");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Magma.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Magma");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Magma.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Magma.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler(ignoreCancelled = true)
	public void Snail(final EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof LivingEntity && event.getDamager() instanceof Player) {
			final Player damager = (Player) event.getDamager();
			if (Main.Magma.contains(damager.getName()) && Math.random() < 0.5) {
				((LivingEntity) event.getEntity()).setFireTicks(150);
			}
		}
	}
}
