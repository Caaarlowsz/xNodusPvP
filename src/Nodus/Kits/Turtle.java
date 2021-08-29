package Nodus.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Turtle implements Listener, CommandExecutor {
	public static Main plugin;

	public Turtle(final Main main) {
		Turtle.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("turtle")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.turtle")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Turtle");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.turtle.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Turtle");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Turtle.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Turtle.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void onEntityDamage(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (Main.turtle.contains(p.getName()) && p.isSneaking()
				&& (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
						|| e.getCause() == EntityDamageEvent.DamageCause.CONTACT
						|| e.getCause() == EntityDamageEvent.DamageCause.CUSTOM
						|| e.getCause() == EntityDamageEvent.DamageCause.DROWNING
						|| e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK
						|| e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION
						|| e.getCause() == EntityDamageEvent.DamageCause.FALL
						|| e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK
						|| e.getCause() == EntityDamageEvent.DamageCause.FIRE
						|| e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK
						|| e.getCause() == EntityDamageEvent.DamageCause.LAVA
						|| e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING
						|| e.getCause() == EntityDamageEvent.DamageCause.MAGIC
						|| e.getCause() == EntityDamageEvent.DamageCause.MELTING
						|| e.getCause() == EntityDamageEvent.DamageCause.POISON
						|| e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE
						|| e.getCause() == EntityDamageEvent.DamageCause.STARVATION
						|| e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION
						|| e.getCause() == EntityDamageEvent.DamageCause.THORNS
						|| e.getCause() == EntityDamageEvent.DamageCause.VOID
						|| e.getCause() == EntityDamageEvent.DamageCause.WITHER)) {
			e.setDamage(1.0);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerTurleDamage(final EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getDamager();
		if (!p.isSneaking()) {
			return;
		}
		if (Main.turtle.contains(p.getName())) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Voce nao pode bater enquanto estiver segurando o shift");
		}
	}
}
