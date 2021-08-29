package Nodus.Kits3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Terrorista implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;
	public static HashMap<String, Arrow> tiros;
	public static List<Player> cooldownm;

	static {
		Terrorista.cooldown = new HashMap<String, Long>();
		Terrorista.tiros = new HashMap<String, Arrow>();
		Terrorista.cooldownm = new ArrayList<Player>();
	}

	public Terrorista(final Main main) {
		Terrorista.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Terrorista")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Terrorista")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Terrorista");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.terrorista.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Terrorista");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.TNT) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Terrorista.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Terrorista.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void stomperApple(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (event.getPlayer().getItemInHand().getType() == Material.TNT
				&& Main.terrorista.contains(event.getPlayer().getName())) {
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK
					|| event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
				event.setCancelled(true);
			}
			if (Terrorista.cooldownm.contains(p)) {
				p.sendMessage("§cCooldown.");
				return;
			}
			final Vector vector = p.getEyeLocation().getDirection();
			vector.multiply(0.0f);
			vector.setY(6.0f);
			p.setVelocity(vector);
			final Location loc = p.getLocation();
			p.getWorld().playSound(loc, Sound.ENDERMAN_TELEPORT, 5.0f, -5.0f);
			Terrorista.cooldownm.add(p);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
				@Override
				public void run() {
					Terrorista.cooldownm.remove(p);
					p.sendMessage("§aJa pode se lancar para cima!");
					p.getWorld().playSound(p.getLocation(), Sound.BURP, 5.0f, 5.0f);
				}
			}, 700L);
		}
	}

	@EventHandler
	public void idknow(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (e.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
				return;
			}
			if (Main.terrorista.contains(p.getName())) {
				e.setCancelled(true);
			} else {
				e.setDamage(e.getDamage() * 2.0);
			}
		}
	}

	@EventHandler
	public void stomper(final EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
			return;
		}
		final Player p = (Player) event.getEntity();
		if (Main.terrorista.contains(p.getName()) && event.getDamage() >= 8.0) {
			event.setDamage(8.0);
			for (final Entity e : p.getNearbyEntities(4.0, 4.0, 4.0)) {
				if (e instanceof Player && !((Player) e).isSneaking()) {
					p.getWorld().createExplosion(p.getLocation(), 1.5f);
				}
			}
		}
	}
}
