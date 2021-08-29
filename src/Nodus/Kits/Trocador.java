package Nodus.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Trocador implements Listener, CommandExecutor {
	public static Main plugin;

	public Trocador(final Main main) {
		Trocador.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Trocador")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.trocador")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Trocador");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.trocador.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					Habilidade.setAbility(p, "Trocador");
					espadameta.setDisplayName("§cSword");
					p.getInventory().addItem(new ItemStack[] { espada });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Trocador.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Trocador.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void Tartaruga(final PlayerToggleSneakEvent e) {
		final Player p = e.getPlayer();
		if (Main.trocador.contains(e.getPlayer().getName())) {
			if (p.isSneaking()) {
				p.getInventory().setChestplate(new ItemStack(Material.AIR));
				p.getInventory().setBoots(new ItemStack(Material.AIR));
			} else {
				p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
				p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
			}
		}
	}
}
