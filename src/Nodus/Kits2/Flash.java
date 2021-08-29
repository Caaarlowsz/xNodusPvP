package Nodus.Kits2;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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

public class Flash implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;

	static {
		Flash.cooldown = new HashMap<String, Long>();
	}

	public Flash(final Main main) {
		Flash.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Flash")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Flash")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Flash");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.freshi.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Flash");
					final ItemStack bow = new ItemStack(Material.REDSTONE_TORCH_ON);
					final ItemMeta bowmeta = bow.getItemMeta();
					bowmeta.setDisplayName("§4Formula 1 Mode! ");
					bow.setItemMeta(bowmeta);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { bow });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Flash.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Flash.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void flash(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.freshi.contains(p.getName()) && p.getItemInHand().getType() == Material.REDSTONE_TORCH_ON) {
			if (!Flash.cooldown.containsKey(p.getName())
					|| Flash.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				e.setCancelled(true);
				p.updateInventory();
				final Block b = p.getTargetBlock(null, 100).getRelative(BlockFace.UP);
				p.teleport(b.getLocation());
				p.sendMessage("§cSe teleporta memo em bixao!");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0f, 10.0f);
				Flash.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L));
				return;
			}
			p.sendMessage(ChatColor.GRAY + "Voce esta restaudando sua energia "
					+ TimeUnit.MILLISECONDS.toSeconds(Flash.cooldown.get(p.getName()) - System.currentTimeMillis())
					+ " segundos.");
		}
	}
}
