package Nodus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Nodus.Main.Main;

public class Ban implements CommandExecutor {

	public Ban(final Main plugin) {
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		final Player p = (Player) sender;
		if (cmd.equalsIgnoreCase("ban") && p.hasPermission("nodus.ban")) {
			if (args.length >= 2) {
				final Player target = p.getServer().getPlayer(args[0]);
				if (target != null) {
					if (!target.isOp()) {
						target.setBanned(true);
						target.kickPlayer(args[1]);
						Bukkit.broadcastMessage("§4" + target.getName() + " foi banido por nao respeitar as regras");
					} else {
						p.sendMessage("§c Voce nao pode banir este cidadao ;-;");
					}
				} else {
					p.sendMessage("§cPlayer offline!");
				}
			} else {
				p.sendMessage("§7Use: /ban <jogador> <motivo>");
			}
		}
		return false;
	}

	public boolean pp(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		final Player p = (Player) sender;
		if (cmd.equalsIgnoreCase("unban") && p.hasPermission("nodus.ban")) {
			if (args.length >= 2) {
				final Player target = p.getServer().getPlayer(args[0]);
				if (target != null) {
					if (!target.isOp()) {
						target.setBanned(true);
						target.kickPlayer(args[1]);
						Bukkit.broadcastMessage("§4" + target.getName() + " foi desbanido.");
					} else {
						p.sendMessage("§c Voce nao pode banir este cidadao ;-;");
					}
				} else {
					p.sendMessage("§cPlayer offline!");
				}
			} else {
				p.sendMessage("§7Use: /unban <jogador> <motivo>");
			}
		}
		return false;
	}
}
