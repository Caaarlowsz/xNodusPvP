package Nodus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Nodus.Main.Main;

public class Kick implements CommandExecutor {

	public Kick(final Main plugin) {
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		final Player p = (Player) sender;
		if (cmd.equalsIgnoreCase("kick") && p.hasPermission("nodus.kick")) {
			if (args.length >= 2) {
				final Player target = p.getServer().getPlayer(args[0]);
				if (target != null) {
					if (!target.isOp()) {
						target.kickPlayer(args[1]);
						Bukkit.broadcastMessage(
								"§c" + target.getName() + " foi kikado por " + args[1] + args[2] + args[3]);
					} else {
						p.sendMessage("§c Voce nao pode kikar este cidadao ;-;");
					}
				} else {
					p.sendMessage("§cPlayer offline!");
				}
			} else {
				p.sendMessage("§7Use: /kick <jogador> <motivo>");
			}
		}
		return false;
	}
}
