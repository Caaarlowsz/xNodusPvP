package Nodus.FuturaMLG;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import Nodus.Main.Main;

public class MlgTeleport implements Listener, CommandExecutor {
	public static Main plugin;

	public MlgTeleport(final Main main) {
		MlgTeleport.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (p.hasPermission("nodus.set") && label.equalsIgnoreCase("setmlg")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /setmlg setar");
				return true;
			}
			if (args[0].equalsIgnoreCase("setar")) {
				Main.config = Main.getPlugin().getConfig();
				p.sendMessage(ChatColor.GREEN + "Setado");
				Main.config.set("MLG.FACIL.X", (Object) p.getLocation().getX());
				Main.config.set("MLG.FACIL.Y", (Object) p.getLocation().getY());
				Main.config.set("MLG.FACIL.Z", (Object) p.getLocation().getZ());
				Main.config.set("MLG.FACIL.PITCH", (Object) p.getLocation().getPitch());
				Main.config.set("MLG.FACIL.YAW", (Object) p.getLocation().getYaw());
				Main.config.set("MLG.FACIL.WORLD", (Object) p.getLocation().getWorld().getName());
				Main.plugin.saveConfig();
				Main.mlg.add(p.getName());
			}
		}
		return false;
	}
}
