package Nodus.Warp;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import Nodus.Main.Habilidade;
import Nodus.Main.Main;

public class SetArena implements Listener, CommandExecutor {
	public static Main plugin;

	public SetArena(final Main main) {
		SetArena.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (p.hasPermission("nodus.set") && label.equalsIgnoreCase("setarena")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /setarena 1/5");
				return true;
			}
			if (args[0].equalsIgnoreCase("1")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado SPAWNPOINT ARENA 1");
				Main.config.set("arena1.x", (Object) p.getLocation().getX());
				Main.config.set("arena1.y", (Object) p.getLocation().getY());
				Main.config.set("arena1.z", (Object) p.getLocation().getZ());
				Main.config.set("arena1.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("arena1.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("arena1.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
			if (args[0].equalsIgnoreCase("2")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado SPAWNPOINT ARENA 2");
				Main.config.set("arena2.x", (Object) p.getLocation().getX());
				Main.config.set("arena2.y", (Object) p.getLocation().getY());
				Main.config.set("arena2.z", (Object) p.getLocation().getZ());
				Main.config.set("arena2.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("arena2.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("arena2.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
			if (args[0].equalsIgnoreCase("3")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado SPAWNPOINT ARENA 3");
				Main.config.set("arena3.x", (Object) p.getLocation().getX());
				Main.config.set("arena3.y", (Object) p.getLocation().getY());
				Main.config.set("arena3.z", (Object) p.getLocation().getZ());
				Main.config.set("arena3.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("arena3.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("arena3.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
			if (args[0].equalsIgnoreCase("4")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado SPAWNPOINT ARENA 4");
				Main.config.set("arena4.x", (Object) p.getLocation().getX());
				Main.config.set("arena4.y", (Object) p.getLocation().getY());
				Main.config.set("arena4.z", (Object) p.getLocation().getZ());
				Main.config.set("arena4.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("arena4.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("arena4.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
			if (args[0].equalsIgnoreCase("5")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado SPAWNPOINT ARENA 5");
				Main.config.set("arena5.x", (Object) p.getLocation().getX());
				Main.config.set("arena5.y", (Object) p.getLocation().getY());
				Main.config.set("arena5.z", (Object) p.getLocation().getZ());
				Main.config.set("arena5.pitch", (Object) p.getLocation().getPitch());
				Main.config.set("arena5.yaw", (Object) p.getLocation().getYaw());
				Main.config.set("arena5.world", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
			}
		}
		return false;
	}

	public static void TeleportArenaRandom(final Player p) {
		final Random dice = new Random();
		final int number = dice.nextInt(4);
		switch (number) {
		case 0: {
			final World w = Bukkit.getServer().getWorld(SetArena.plugin.getConfig().getString("arena1.world"));
			final double x = SetArena.plugin.getConfig().getDouble("arena1.x");
			final double y = SetArena.plugin.getConfig().getDouble("arena1.y");
			final double z = SetArena.plugin.getConfig().getDouble("arena1.z");
			final Location lobby = new Location(w, x, y, z);
			lobby.setPitch((float) SetArena.plugin.getConfig().getDouble("arena1.pitch"));
			lobby.setYaw((float) SetArena.plugin.getConfig().getDouble("arena1.yaw"));
			p.getWorld().playEffect(lobby, Effect.ENDER_SIGNAL, 5);
			p.getPlayer().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
			p.teleport(lobby);
			p.sendMessage(Main.getInstance().getConfig().getString("MensagemTeleportArena1").replaceAll("&", "§")
					.replaceAll("%kit", Habilidade.getAbility(p)).replaceAll("%p", p.getName()));
			break;
		}
		case 1: {
			final World w2 = Bukkit.getServer().getWorld(SetArena.plugin.getConfig().getString("arena2.world"));
			final double x2 = SetArena.plugin.getConfig().getDouble("arena2.x");
			final double y2 = SetArena.plugin.getConfig().getDouble("arena2.y");
			final double z2 = SetArena.plugin.getConfig().getDouble("arena2.z");
			final Location lobby2 = new Location(w2, x2, y2, z2);
			lobby2.setPitch((float) SetArena.plugin.getConfig().getDouble("arena2.pitch"));
			lobby2.setYaw((float) SetArena.plugin.getConfig().getDouble("arena2.yaw"));
			p.getWorld().playEffect(lobby2, Effect.ENDER_SIGNAL, 5);
			p.getPlayer().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
			p.teleport(lobby2);
			p.sendMessage(Main.getInstance().getConfig().getString("MensagemTeleportArena2").replaceAll("&", "§")
					.replaceAll("%kit", Habilidade.getAbility(p)).replaceAll("%p", p.getName()));
			break;
		}
		case 2: {
			final World w3 = Bukkit.getServer().getWorld(SetArena.plugin.getConfig().getString("arena3.world"));
			final double x3 = SetArena.plugin.getConfig().getDouble("arena3.x");
			final double y3 = SetArena.plugin.getConfig().getDouble("arena3.y");
			final double z3 = SetArena.plugin.getConfig().getDouble("arena3.z");
			final Location lobby3 = new Location(w3, x3, y3, z3);
			lobby3.setPitch((float) SetArena.plugin.getConfig().getDouble("arena3.pitch"));
			lobby3.setYaw((float) SetArena.plugin.getConfig().getDouble("arena3.yaw"));
			p.getWorld().playEffect(lobby3, Effect.ENDER_SIGNAL, 5);
			p.getPlayer().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
			p.teleport(lobby3);
			p.sendMessage(Main.getInstance().getConfig().getString("MensagemTeleportArena3").replaceAll("&", "§")
					.replaceAll("%kit", Habilidade.getAbility(p)).replaceAll("%p", p.getName()));
			break;
		}
		case 3: {
			final World w4 = Bukkit.getServer().getWorld(SetArena.plugin.getConfig().getString("arena4.world"));
			final double x4 = SetArena.plugin.getConfig().getDouble("arena4.x");
			final double y4 = SetArena.plugin.getConfig().getDouble("arena4.y");
			final double z4 = SetArena.plugin.getConfig().getDouble("arena4.z");
			final Location lobby4 = new Location(w4, x4, y4, z4);
			lobby4.setPitch((float) SetArena.plugin.getConfig().getDouble("arena4.pitch"));
			lobby4.setYaw((float) SetArena.plugin.getConfig().getDouble("arena4.yaw"));
			p.getWorld().playEffect(lobby4, Effect.ENDER_SIGNAL, 5);
			p.getPlayer().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
			p.teleport(lobby4);
			p.sendMessage(Main.getInstance().getConfig().getString("MensagemTeleportArena2").replaceAll("&", "§")
					.replaceAll("%kit", Habilidade.getAbility(p)).replaceAll("%p", p.getName()));
			break;
		}
		case 4: {
			final World w5 = Bukkit.getServer().getWorld(SetArena.plugin.getConfig().getString("arena5.world"));
			final double x5 = SetArena.plugin.getConfig().getDouble("arena5.x");
			final double y5 = SetArena.plugin.getConfig().getDouble("arena5.y");
			final double z5 = SetArena.plugin.getConfig().getDouble("arena5.z");
			final Location lobby5 = new Location(w5, x5, y5, z5);
			lobby5.setPitch((float) SetArena.plugin.getConfig().getDouble("arena5.pitch"));
			lobby5.setYaw((float) SetArena.plugin.getConfig().getDouble("arena5.yaw"));
			p.getWorld().playEffect(lobby5, Effect.ENDER_SIGNAL, 5);
			p.getPlayer().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 5.0f, 5.0f);
			p.teleport(lobby5);
			p.sendMessage(Main.getInstance().getConfig().getString("MensagemTeleportArena5").replaceAll("&", "§")
					.replaceAll("%kit", Habilidade.getAbility(p)).replaceAll("%p", p.getName()));
			break;
		}
		}
	}
}
