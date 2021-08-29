package Nodus.Kits2;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Event.ServerName;
import Nodus.Main.Habilidade;
import Nodus.Main.Main;
import Nodus.Warp.SetArena;

public class Grandpa implements Listener, CommandExecutor {
	public static Main plugin;
	public static HashMap<String, Long> cooldown;
	public static HashMap<String, Egg> teias;
	public int cooldown11;
	public String cooldownMessage;
	ArrayList<String> thor;

	static {
		Grandpa.cooldown = new HashMap<String, Long>();
		Grandpa.teias = new HashMap<String, Egg>();
	}

	public Grandpa(final Main main) {
		this.cooldown11 = 40;
		this.cooldownMessage = ChatColor.RED + "Aguarde!";
		this.thor = new ArrayList<String>();
		Grandpa.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Grandpa")) {
			if (!Main.usandokit.contains(p.getName())) {
				if (p.hasPermission("kit.Grandpa")) {
					p.sendMessage(String.valueOf(ServerName.nomedokit) + " Grandpa");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
					Main.usandokit.add(p.getName());
					Main.Grandpa.add(sender.getName());
					p.getInventory().clear();
					Habilidade.setAbility(p, "Grandpa");
					final ItemStack espada = new ItemStack(Material.STONE_SWORD);
					final ItemMeta espadameta = espada.getItemMeta();
					espadameta.setDisplayName("§cSword");
					final ItemStack bow = new ItemStack(Material.STICK);
					bow.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
					final ItemMeta bowmeta = bow.getItemMeta();
					bow.setItemMeta(bowmeta);
					p.getInventory().addItem(new ItemStack[] { espada });
					p.getInventory().addItem(new ItemStack[] { bow });
					Main.giveSoup(p, 34);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					SetArena.TeleportArenaRandom(p);
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							Grandpa.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						Grandpa.plugin.getConfig().getString("Um_Kit_Por_Vida")));
			}
			return true;
		}
		return false;
	}
}
