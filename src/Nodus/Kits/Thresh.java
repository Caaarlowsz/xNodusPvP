package Nodus.Kits;

import java.util.ArrayList;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Thresh implements Listener, CommandExecutor {
	public static Main plugin;
	public static List<Player> cooldownm;

	static {
		Thresh.cooldownm = new ArrayList<Player>();
	}

	public Thresh(final Main main) {
		Thresh.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Thresh")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Thresh")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Thresh");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.qd.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("?cSword");
					final ItemStack bow = new ItemStack(Material.LEASH);
					final ItemMeta bowmeta = bow.getItemMeta();
					Habilidade.setAbility(p, "Thresh");
					bowmeta.setDisplayName("?bThreshaum");
					bow.setItemMeta(bowmeta);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { bow });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Thresh.plugin.getConfig().getString("Sem_Permiss\u00c3?o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Thresh.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void BloodClick(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (event.getPlayer().getItemInHand().getType() == Material.LEASH
				&& Main.qd.contains(event.getPlayer().getName())) {
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				event.setCancelled(true);
			}
			if (Thresh.cooldownm.contains(p)) {
				p.sendMessage("?c?oAguarde o cooldown acabar!");
			} else {
				final Vector velo1 = p.getLocation().getDirection().normalize().multiply(30);
				final Snowball boladenve = (Snowball) p.launchProjectile(Snowball.class);
				boladenve.setVelocity(velo1);
				final Location location = p.getEyeLocation();
				final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 30);
				while (blocksToAdd.hasNext()) {
					final Location blockToAdd = blocksToAdd.next().getLocation();
					p.getWorld().playEffect(blockToAdd, Effect.SMOKE, 5);
					Thresh.cooldownm.add(p);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							Thresh.cooldownm.remove(p);
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
	public void DanoOstenta$aum(final EntityDamageByEntityEvent e) {
		final Entity ent = e.getEntity();
		final Entity damager = e.getDamager();
		if (ent instanceof Player) {
			final Player hit = (Player) ent;
			if (damager instanceof Snowball) {
				final Snowball snowball = (Snowball) damager;
				if (snowball.getShooter() instanceof Player) {
					final Player shooter = (Player) snowball.getShooter();
					if (!Main.qd.contains(shooter.getName())) {
						return;
					}
					final Location ploc = shooter.getLocation();
					hit.teleport(ploc);
					hit.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
					hit.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
				}
			}
		}
	}
}
