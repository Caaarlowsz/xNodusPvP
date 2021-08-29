package Nodus.Kits;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Thor implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Thor.cooldown = new HashMap<String, Long>();
	}

	public Thor(final Main main) {
		Thor.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("thor")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.thor")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Thor");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.thor.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Thor");
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_AXE) });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Thor.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Thor.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void lancar(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.thor.contains(p.getName()) && p.getItemInHand().getType() == Material.GOLD_AXE) {
			e.setCancelled(true);
			p.updateInventory();
			if (!Thor.cooldown.containsKey(p.getName())
					|| Thor.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				final Block b = e.getClickedBlock();
				Habilidade.strikeLightning(b.getLocation());
				Thor.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10L));
				p.playSound(p.getLocation(), Sound.IRONGOLEM_THROW, 1.0f, 1.0f);
				return;
			}
			p.sendMessage(
					new StringBuilder().append(ChatColor.RED)
							.append(TimeUnit.MILLISECONDS
									.toSeconds(Thor.cooldown.get(p.getName()) - System.currentTimeMillis()))
							.toString());
		}
	}
}
