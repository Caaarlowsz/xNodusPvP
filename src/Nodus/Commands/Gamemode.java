package Nodus.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Gamemode implements CommandExecutor, Listener {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		final Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gm") && sender.hasPermission("nodus.gm")) {
			if (args.length != 1) {
				player.sendMessage("§aGm 0 - Gm 1");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("0")) {
					player.sendMessage("§7Voce entrou em modo §4Survival");
					player.setGameMode(GameMode.SURVIVAL);
				}
				if (args[0].equalsIgnoreCase("1")) {
					player.sendMessage("§7Voce entrou em modo §4Criativo");
					player.setGameMode(GameMode.CREATIVE);
				}
				return true;
			}
		}
		return false;
	}
}
