package Nodus.Kits2;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
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

public class Scorpion implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;
	public static HashMap<String, Egg> teias;

	static {
		Scorpion.cooldown = new HashMap<String, Long>();
		Scorpion.teias = new HashMap<String, Egg>();
	}

	public Scorpion(final Main main) {
		Scorpion.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("scorpion")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Scorpion")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Scorpion");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.scorpionarrow.add(sender.getName());
					Habilidade.setAbility(p, "Scorpion");
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					final ItemStack iron = new ItemStack(Material.ARROW);
					final ItemMeta ironm = iron.getItemMeta();
					ironm.setDisplayName("§6Get Over HERE!");
					iron.setItemMeta(ironm);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { iron });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Scorpion.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Scorpion.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void lancar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		final Vector velo1 = p.getLocation().getDirection().normalize().multiply(5);
		velo1.add(new Vector(Math.random() * 0.0 + 0.0, 0.0, 0.0));
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.scorpionarrow.contains(p.getName()) && p.getItemInHand().getType() == Material.ARROW) {
			e.setCancelled(true);
			p.updateInventory();
			if (!Scorpion.cooldown.containsKey(p.getName())
					|| Scorpion.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				if (Main.SemPvP(p)) {
					p.sendMessage(ChatColor.RED + "Apos 20 segundos sua habilidade estara pronta!");
					p.playSound(p.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
					p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 7);
					p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 7);
					p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 7);
					p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 7);
					p.playEffect(p.getLocation(), Effect.SMOKE, 7);
					p.playEffect(p.getLocation(), Effect.SMOKE, 7);
					p.playEffect(p.getLocation(), Effect.SMOKE, 7);
					p.playEffect(p.getLocation(), Effect.SMOKE, 7);
					p.sendMessage("§3§lGet Over HERE!");
					((Arrow) p.launchProjectile(Arrow.class)).setVelocity(velo1);
					final Location location = p.getEyeLocation();
					final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 50);
					Scorpion.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L));
					if (blocksToAdd.hasNext()) {
						final Location blockToAdd = blocksToAdd.next().getLocation();
						p.getWorld().playEffect(blockToAdd, Effect.STEP_SOUND, (Object) Material.FIRE, 20);
						p.playSound(blockToAdd, Sound.WITHER_SHOOT, 3.0f, 3.0f);
						return;
					}
					p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
				} else {
					p.sendMessage(ChatColor.RED + "Voce esta em cooldown");
				}
			}
		}
	}

	@EventHandler
	public void arrow(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow && e.getEntity() instanceof Player) {
			final Arrow s = (Arrow) e.getDamager();
			if (s.getShooter() instanceof Player) {
				final Player shooter = (Player) s.getShooter();
				if (Main.scorpionarrow.contains(shooter.getName())) {
					final Location shooterLoc = shooter.getLocation();
					e.getEntity().teleport(shooterLoc);
					shooter.getPlayer().getWorld().playEffect(shooterLoc, Effect.ENDER_SIGNAL, 10);
					shooter.getPlayer().getWorld().playEffect(shooterLoc, Effect.EXTINGUISH, 10);
					shooter.getWorld().playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.2f);
				}
			}
		}
	}
}
