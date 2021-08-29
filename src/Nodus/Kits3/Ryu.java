package Nodus.Kits3;

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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Ryu implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Ryu.cooldown = new HashMap<String, Long>();
	}

	public Ryu(final Main main) {
		Ryu.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("ryu")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.ryu")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Ryu");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.ryu.add(sender.getName());
					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BEACON) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					Habilidade.setAbility(p, "Ryu");
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Ryu.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Ryu.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void hadouken(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.ryu.contains(p.getName()) && p.getItemInHand().getType() == Material.BEACON) {
			if (!Ryu.cooldown.containsKey(p.getName()) || Ryu.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				e.setCancelled(true);
				p.updateInventory();
				final Location location = p.getEyeLocation();
				final BlockIterator blocksToAdd = new BlockIterator(location, 0.0, 40);
				while (blocksToAdd.hasNext()) {
					final Location blockToAdd = blocksToAdd.next().getLocation();
					p.getWorld().playEffect(blockToAdd, Effect.STEP_SOUND, (Object) Material.LAVA, 20);
					p.playSound(blockToAdd, Sound.IRONGOLEM_THROW, 3.0f, 3.0f);
				}
				final Snowball h = (Snowball) p.launchProjectile(Snowball.class);
				final Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
				h.setVelocity(velo1);
				h.setMetadata("hadouken", (MetadataValue) new FixedMetadataValue(Main.plugin, (Object) true));
				Ryu.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L));
				return;
			}
			p.sendMessage(ChatColor.RED + "Faltam "
					+ TimeUnit.MILLISECONDS.toSeconds(Ryu.cooldown.get(p.getName()) - System.currentTimeMillis())
					+ " segundos para poder usar novamente.");
		}
	}

	@EventHandler
	public void DanoOstentasaum(final EntityDamageByEntityEvent e) {
		final Entity ent = e.getEntity();
		final Entity damager = e.getDamager();
		if (ent instanceof Player) {
			if (damager instanceof Snowball) {
				final Snowball snowball = (Snowball) damager;
				if (snowball.getShooter() instanceof Player) {
					final Player shooter = (Player) snowball.getShooter();
					if (!Main.ryu.contains(shooter.getName())) {
						return;
					}
					e.setDamage(8.0);
				}
			}
		}
	}
}
