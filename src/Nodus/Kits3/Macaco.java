package Nodus.Kits3;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

public class Macaco implements Listener, CommandExecutor {
	public static Main plugin;

	public Macaco(final Main main) {
		Macaco.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Macaco")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Macaco")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Macaco");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Macaco.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Macaco");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Macaco.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Macaco.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void soldierEvent(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		if (!Main.Macaco.contains(player.getName())) {
			return;
		}
		if (player.getItemInHand().getType() != Material.STONE_SWORD) {
			return;
		}
		player.setVelocity(new Vector(0.0f, 0.7f, 0.0f));
	}

	@EventHandler
	public void soldierContinue(final EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		final Player player = (Player) event.getEntity();
		if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
			return;
		}
		if (!Main.Macaco.contains(player.getName())) {
			return;
		}
		event.setCancelled(true);
	}
}
