package Nodus.Kits3;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Granadier implements Listener, CommandExecutor {
	public static Main plugin;

	public Granadier(final Main main) {
		Granadier.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("granadier")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.granadier")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Granadier");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.granadier.add(sender.getName());
					p.getInventory().clear();
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.SNOW_BALL, 5) });
					Habilidade.setAbility(p, "Granadier");
					Main.giveSoup(p, 33);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Granadier.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Granadier.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void lancar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.granadier.contains(p.getName()) && p.getItemInHand().getType() == Material.SNOW_BALL) {
			e.setCancelled(true);
			p.updateInventory();
			p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.SNOW_BALL, 1) });
			final Snowball granada = (Snowball) p.launchProjectile(Snowball.class);
			granada.setMetadata("granadier", (MetadataValue) new FixedMetadataValue(Main.plugin, (Object) true));
			p.playSound(p.getLocation(), Sound.FUSE, 1.0f, 1.0f);
			p.sendMessage(ChatColor.GOLD + "Granada jogada!");
		}
	}

	@EventHandler
	public void explosao(final ProjectileHitEvent e) {
		if (e.getEntity() instanceof Snowball) {
			final Snowball b = (Snowball) e.getEntity();
			if (b.hasMetadata("granadier")) {
				b.getWorld().createExplosion(b.getLocation(), 3.0f);
			}
		}
	}

	@EventHandler
	public void dano(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION && Main.granadier.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
}
