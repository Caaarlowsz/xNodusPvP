package Nodus.Kits2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Sniper implements Listener, CommandExecutor {
	public static HashMap<String, Arrow> tiros;
	public static Main plugin;
	public static List<Player> cooldownm;

	static {
		Sniper.tiros = new HashMap<String, Arrow>();
		Sniper.cooldownm = new ArrayList<Player>();
	}

	public Sniper(final Main main) {
		Sniper.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] Args) {
		if (label.equalsIgnoreCase("sniper")) {
			final Player p = (Player) sender;
			if (!p.hasPermission("kit.sniper")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Sniper.plugin.getConfig().getString("Sem_Permiss\u00c3?o_Kit")));
				return true;
			}
			if (Main.usandokit.contains(p.getName())) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Sniper.plugin.getConfig().getString("Um_Kit_Por_Vida")));
				return true;
			}
			final ItemStack espada = new ItemStack(Material.STONE_SWORD);
			final ItemMeta kespada = espada.getItemMeta();
			espada.setItemMeta(kespada);
			Habilidade.setAbility(p, "Sniper");
			final ItemStack snip = new ItemStack(Material.IRON_BARDING);
			final ItemMeta ksnip = snip.getItemMeta();
			ksnip.setDisplayName("?cCheyTac");
			snip.setItemMeta(ksnip);
			p.sendMessage(String.valueOf(ServerName.nomedokit) + "Sniper");
			p.getInventory().clear();
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
			Main.giveSoup(p, 35);
			p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
			p.getInventory().setItem(0, espada);
			p.getInventory().setItem(1, snip);
			Main.usandokit.add(p.getName());
			Main.sniper.add(p.getName());
			SetArena.TeleportArenaRandom(p);
		}
		return false;
	}

	@EventHandler
	public void BloodClick(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (event.getPlayer().getItemInHand().getType() == Material.IRON_BARDING
				&& Main.sniper.contains(event.getPlayer().getName())) {
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				event.setCancelled(true);
			}
			if (Sniper.cooldownm.contains(p)) {
				p.sendMessage("?c?oAguarde o cooldown acabar!");
			} else {
				final Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
				final Snowball boladenve = (Snowball) p.launchProjectile(Snowball.class);
				boladenve.setVelocity(velo1);
				final Location location = p.getEyeLocation();
				final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 30);
				while (blocksToAdd.hasNext()) {
					final Location blockToAdd = blocksToAdd.next().getLocation();
					p.getWorld().playEffect(blockToAdd, Effect.MOBSPAWNER_FLAMES, 5);
					Sniper.cooldownm.add(p);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							Sniper.cooldownm.remove(p);
						}
					}, 200L);
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
					@Override
					public void run() {
						p.sendMessage("?a?oVoce pode usar novamente!");
					}
				}, 200L);
			}
		}
	}

	@EventHandler
	public void DanoOstentasaum(final EntityDamageByEntityEvent e) {
		final Entity ent = e.getEntity();
		final Entity damager = e.getDamager();
		if (ent instanceof Player) {
			final Player hit = (Player) ent;
			if (damager instanceof Snowball) {
				final Snowball snowball = (Snowball) damager;
				if (snowball.getShooter() instanceof Player) {
					final Player shooter = (Player) snowball.getShooter();
					if (!Main.sniper.contains(shooter.getName())) {
						return;
					}
					hit.setHealth(0.0);
				}
			}
		}
	}
}
