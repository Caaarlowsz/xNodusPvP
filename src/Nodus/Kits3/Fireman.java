package Nodus.Kits3;

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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Fireman implements Listener, CommandExecutor {
	public static Main plugin;

	public Fireman(final Main main) {
		Fireman.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("fireman")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.fireman")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Fireman");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.fireman.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("?cSword");
					Habilidade.setAbility(p, "FireMan");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Fireman.plugin.getConfig().getString("Sem_Permiss\u00c3?o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Fireman.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void damage(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (Main.fireman.contains(p.getName()) && (e.getCause() == EntityDamageEvent.DamageCause.LAVA
					|| e.getCause() == EntityDamageEvent.DamageCause.FIRE
					|| e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void Fogo(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			final Player p = (Player) e.getDamager();
			if (Main.fireman.contains(p.getName()) && p.getInventory().getItemInHand() != null
					&& p.getInventory().getItemInHand().getType() == Material.STONE_SWORD
					&& e.getEntity() instanceof LivingEntity) {
				final LivingEntity en = (LivingEntity) e.getEntity();
				if (en.isDead()) {
					return;
				}
				en.setFireTicks(100);
			}
		}
	}
}
