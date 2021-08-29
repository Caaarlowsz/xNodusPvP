package Nodus.Warp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Nodus.Main.Main;

public class Set1v1 implements Listener, CommandExecutor {
	public static Main plugin;

	public Set1v1(final Main main) {
		Set1v1.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (p.hasPermission("nodus.set") && label.equalsIgnoreCase("set1v1")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /set1v1 pos1/pos2/spawn");
				return true;
			}
			if (args[0].equalsIgnoreCase("spawn")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado");
				Main.config.set("spawn3.y", (Object) p.getLocation().getY());
				Main.config.set("spawn3.z", (Object) p.getLocation().getZ());
				Main.config.set("spawn3.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("spawn3.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("spawn3.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
			if (args[0].equalsIgnoreCase("pos2")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado");
				Main.config.set("pos2.x", (Object) p.getLocation().getX());
				Main.config.set("pos2.y", (Object) p.getLocation().getY());
				Main.config.set("pos2.z", (Object) p.getLocation().getZ());
				Main.config.set("pos2.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("pos2.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("pos2.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
			if (args[0].equalsIgnoreCase("pos1")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado");
				Main.config.set("pos1.x", (Object) p.getLocation().getX());
				Main.config.set("pos1.y", (Object) p.getLocation().getY());
				Main.config.set("pos1.z", (Object) p.getLocation().getZ());
				Main.config.set("pos1.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("pos1.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("pos1.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
		}
		return false;
	}

	public static void TpP1(final Player p, final String l) {
		final World w = Bukkit.getServer().getWorld(Set1v1.plugin.getConfig().getString(String.valueOf(l) + ".world"));
		final double x = Set1v1.plugin.getConfig().getDouble(String.valueOf(l) + ".x");
		final double y = Set1v1.plugin.getConfig().getDouble(String.valueOf(l) + ".y");
		final double z = Set1v1.plugin.getConfig().getDouble(String.valueOf(l) + ".z");
		final Location lobby = new Location(w, x, y, z);
		lobby.setPitch((float) Set1v1.plugin.getConfig().getDouble(String.valueOf(l) + ".pitch"));
		lobby.setYaw((float) Set1v1.plugin.getConfig().getDouble(String.valueOf(l) + ".yaw"));
		p.getWorld().playEffect(lobby, Effect.ENDER_SIGNAL, 5);
		p.getPlayer().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
		p.teleport(lobby);
	}

	public static void daritens(final Player p) {
		final ItemStack espada = new ItemStack(Material.BLAZE_ROD);
		final ItemMeta espadameta = espada.getItemMeta();
		espadameta.setDisplayName("§61v1");
		espada.setItemMeta(espadameta);
		final ItemStack bow = new ItemStack(Material.REDSTONE);
		final ItemMeta bowmeta = bow.getItemMeta();
		bowmeta.setDisplayName("§4Sair");
		bow.setItemMeta(bowmeta);
		final ItemStack quick = new ItemStack(Material.SLIME_BALL);
		final ItemMeta bowmeta2 = bow.getItemMeta();
		bowmeta2.setDisplayName("§a1v1-Automatico");
		quick.setItemMeta(bowmeta2);
		p.getInventory().clear();
		p.getInventory().setItem(0, espada);
		p.getInventory().setItem(4, quick);
		p.getInventory().setItem(8, bow);
	}
}
