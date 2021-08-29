package Nodus.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Archer implements Listener, CommandExecutor {
	public static Main plugin;

	public Archer(final Main main) {
		Archer.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("archer")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.archer")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + "Archer");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.archer.add(sender.getName());
					p.getInventory().clear();
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					Habilidade.setAbility(p, "Archer");
					p.getInventory().addItem(new ItemStack[] { espada });
					final ItemStack bow = new ItemStack(Material.BOW);
					final ItemMeta bowmeta = bow.getItemMeta();
					bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
					bowmeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
					bowmeta.setDisplayName("§cBow Archer");
					bow.setItemMeta(bowmeta);
					final ItemStack bow2 = new ItemStack(Material.ARROW);
					p.getInventory().setItem(1, bow);
					p.getInventory().setItem(2, bow2);
					Main.giveSoup(p, 33);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Archer.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Archer.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}
}
