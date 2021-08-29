package Nodus.Warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Nodus.Main.Main;

public class SetParkour implements CommandExecutor {
	public static Main plugin;

	public SetParkour(final Main main) {
		SetParkour.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (cmd.getName().equalsIgnoreCase("setparkour") && sender instanceof Player) {
			if (!sender.hasPermission("nodus.set")) {
				final Player p1 = (Player) sender;
				p1.sendMessage("§cVoce nao tem permissao!");
			}
			if (sender.hasPermission("nodus.set")) {
				final Player p1 = (Player) sender;
				SetParkour.plugin.getConfig().set("parkour.x", (Object) p1.getLocation().getX());
				SetParkour.plugin.getConfig().set("parkour.y", (Object) p1.getLocation().getY());
				SetParkour.plugin.getConfig().set("parkour.z", (Object) p1.getLocation().getZ());
				SetParkour.plugin.getConfig().set("parkour.pitch", (Object) p1.getLocation().getPitch());
				SetParkour.plugin.getConfig().set("parkour.yaw", (Object) p1.getLocation().getYaw());
				SetParkour.plugin.getConfig().set("parkour.world", (Object) p1.getLocation().getWorld().getName());
				SetParkour.plugin.saveConfig();
				p1.sendMessage("§a [!] Warp parkour setada com sucesso");
			}
		}
		return false;
	}
}
