package Nodus.Kits2;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Dj implements Listener, CommandExecutor {
	public static Main plugin;

	public Dj(final Main main) {
		Dj.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("dj")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.dj")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " dj");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Dj.add(sender.getName());
					Habilidade.NomeDoKit("Dj");
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					Habilidade.setAbility(p, "Dj");
					espadameta.setDisplayName("§cSword");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 35);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Dj.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Dj.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void SwordKit(final PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		final PlayerInventory inv = player.getInventory();
		if (Main.Dj.contains(player.getName()) && (player.getItemInHand().getType() == Material.WOOD_SWORD
				|| player.getItemInHand().getType() == Material.STONE_SWORD
				|| player.getItemInHand().getType() == Material.GOLD_SWORD
				|| player.getItemInHand().getType() == Material.STONE_SWORD
				|| player.getItemInHand().getType() == Material.DIAMOND_SWORD)) {
			player.updateInventory();
			final Random dice = new Random();
			final int number = dice.nextInt(10);
			switch (number) {
			case 0: {
				player.getItemInHand().setType(Material.GOLD_SWORD);
				player.updateInventory();
				break;
			}
			case 1: {
				inv.setArmorContents(new ItemStack[] { new ItemStack(Material.AIR) });
				player.getItemInHand().setType(Material.WOOD_SWORD);
				player.updateInventory();
				break;
			}
			case 2: {
				inv.setArmorContents(new ItemStack[] { new ItemStack(Material.AIR) });
				player.getItemInHand().setType(Material.STONE_SWORD);
				player.updateInventory();
				break;
			}
			case 3: {
				player.getItemInHand().setType(Material.DIAMOND_SWORD);
				player.updateInventory();
				break;
			}
			case 4: {
				inv.setArmorContents(new ItemStack[] { new ItemStack(Material.AIR) });
				player.getItemInHand().setType(Material.STONE_SWORD);
				player.updateInventory();
				break;
			}
			}
		}
	}
}
