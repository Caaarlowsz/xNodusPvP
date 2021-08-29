package Nodus.Warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Nodus.Main.Main;

public class SetChallenge implements CommandExecutor {
	public static Main plugin;

	public SetChallenge(final Main main) {
		SetChallenge.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (cmd.getName().equalsIgnoreCase("setchallenge") && sender instanceof Player) {
			if (!sender.hasPermission("nodus.set")) {
				final Player p1 = (Player) sender;
				p1.sendMessage("§cVoce nao tem permissao!");
			}
			if (sender.hasPermission("nodus.set")) {
				final Player p1 = (Player) sender;
				SetChallenge.plugin.getConfig().set("challenge.x", (Object) p1.getLocation().getX());
				SetChallenge.plugin.getConfig().set("challenge.y", (Object) p1.getLocation().getY());
				SetChallenge.plugin.getConfig().set("challenge.z", (Object) p1.getLocation().getZ());
				SetChallenge.plugin.getConfig().set("challenge.pitch", (Object) p1.getLocation().getPitch());
				SetChallenge.plugin.getConfig().set("challenge.yaw", (Object) p1.getLocation().getYaw());
				SetChallenge.plugin.getConfig().set("challenge.world", (Object) p1.getLocation().getWorld().getName());
				SetChallenge.plugin.saveConfig();
				p1.sendMessage("§a [!] Warp Challenge setada com sucesso");
			}
		}
		return false;
	}

	public boolean onCommand1(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (cmd.getName().equalsIgnoreCase("setwaterchallenge") && sender instanceof Player) {
			if (!sender.hasPermission("kitpvp.setwarp")) {
				final Player p1 = (Player) sender;
				p1.sendMessage("§cVoce nao tem permissao!");
			}
			if (sender.hasPermission("kitpvp.setwarp")) {
				final Player p1 = (Player) sender;
				SetChallenge.plugin.getConfig().set("w.x", (Object) p1.getLocation().getX());
				SetChallenge.plugin.getConfig().set("w.y", (Object) p1.getLocation().getY());
				SetChallenge.plugin.getConfig().set("w.z", (Object) p1.getLocation().getZ());
				SetChallenge.plugin.getConfig().set("w.pitch", (Object) p1.getLocation().getPitch());
				SetChallenge.plugin.getConfig().set("w.yaw", (Object) p1.getLocation().getYaw());
				SetChallenge.plugin.getConfig().set("w.world", (Object) p1.getLocation().getWorld().getName());
				SetChallenge.plugin.saveConfig();
				p1.sendMessage("§a [!] Warp WaterChallenge setada com sucesso");
			}
		}
		return false;
	}
}
