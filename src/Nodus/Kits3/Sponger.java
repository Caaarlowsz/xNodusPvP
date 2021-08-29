package Nodus.Kits3;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Sponger implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;
	public static HashMap<String, Egg> teias;

	static {
		Sponger.cooldown = new HashMap<String, Long>();
		Sponger.teias = new HashMap<String, Egg>();
	}

	public Sponger(final Main main) {
		Sponger.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("sponger")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.sponger")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Sponger");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.sponger.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					final ItemStack bow = new ItemStack(Material.SPONGE);
					final ItemMeta bowmeta = bow.getItemMeta();
					bowmeta.setDisplayName("§e Spongita");
					Habilidade.setAbility(p, "Sponger");
					bow.setItemMeta(bowmeta);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { bow });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Sponger.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Sponger.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void lancar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.sponger.contains(p.getName()) && p.getItemInHand().getType() == Material.SPONGE) {
			e.setCancelled(true);
			p.updateInventory();
			if (!Sponger.cooldown.containsKey(p.getName())
					|| Sponger.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				if (Main.SemPvP(p)) {
					final Egg teia = (Egg) p.launchProjectile(Egg.class);
					Sponger.teias.put(p.getName(), teia);
					Sponger.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(29L));
					p.playSound(p.getLocation(), Sound.BLAZE_HIT, 1.0f, 1.0f);
					p.playEffect(p.getLocation(), Effect.SMOKE, 100);
					return;
				}
				p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
			} else {
				p.sendMessage(ChatColor.RED + "Faltam "
						+ TimeUnit.MILLISECONDS
								.toSeconds(Sponger.cooldown.get(p.getName()) - System.currentTimeMillis())
						+ " segundos para poder usar novamente.");
			}
		}
	}

	@EventHandler
	public void teia(final ProjectileHitEvent e) {
		if (e.getEntity() instanceof Egg && e.getEntity().getShooter() instanceof Player) {
			final Egg teia = (Egg) e.getEntity();
			final Player p = (Player) e.getEntity().getShooter();
			if (Sponger.teias.containsKey(p.getName()) && teia == Sponger.teias.get(p.getName())) {
				Sponger.teias.remove(p.getName());
				final Block b = teia.getLocation().getBlock();
				b.setType(Material.SPONGE);
				p.getPlayer().teleport(b.getLocation());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Sponger.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (b.getType() == Material.SPONGE) {
									b.setType(Material.AIR);
								}
							}
						}, 100L);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerStomp(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
			return;
		}
		if (Main.sponger.contains(p.getName())) {
			for (final Entity ent : p.getNearbyEntities(4.0, 4.0, 4.0)) {
				if (ent instanceof Player) {
					final Player plr = (Player) ent;
					if (e.getDamage() <= 4.0) {
						e.setCancelled(true);
						return;
					}
					if (plr.isSneaking()) {
						plr.damage(6.0, (Entity) p);
						plr.sendMessage(
								ChatColor.GRAY + "Voce Foi pisoteado Pelo sponger: " + ChatColor.AQUA + p.getName());
					} else {
						plr.damage(e.getDamage(), (Entity) p);
						plr.sendMessage(ChatColor.GRAY + "Voce Foi Pisoteado Por: " + ChatColor.AQUA + p.getName());
						plr.damage((double) p.getFallDistance());
					}
				}
			}
			e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
			e.setDamage(4.0);
		}
	}
}
