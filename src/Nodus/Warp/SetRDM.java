package Nodus.Warp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import Nodus.Main.Main;

public class SetRDM implements Listener, CommandExecutor {
	public static Main plugin;

	public SetRDM(final Main main) {
		SetRDM.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (cmd.getName().equalsIgnoreCase("setrdm") && sender instanceof Player) {
			if (!sender.hasPermission("nodus.set")) {
				final Player p = (Player) sender;
				p.sendMessage("§cVoce nao tem permissao!");
			}
			if (sender.hasPermission("nodus.set")) {
				final Player p = (Player) sender;
				SetRDM.plugin.getConfig().set("rdm.x", (Object) p.getLocation().getX());
				SetRDM.plugin.getConfig().set("rdm.y", (Object) p.getLocation().getY());
				SetRDM.plugin.getConfig().set("rdm.z", (Object) p.getLocation().getZ());
				SetRDM.plugin.getConfig().set("rdm.pitch", (Object) p.getLocation().getPitch());
				SetRDM.plugin.getConfig().set("rdm.yaw", (Object) p.getLocation().getYaw());
				SetRDM.plugin.getConfig().set("rdm.world", (Object) p.getLocation().getWorld().getName());
				SetRDM.plugin.saveConfig();
				p.sendMessage("§a [!] Warp RDM setada com sucesso");
			}
			return true;
		}
		return false;
	}
}
