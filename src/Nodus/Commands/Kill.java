package Nodus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Kill implements CommandExecutor, Listener {
	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		if (cmd.equalsIgnoreCase("suicide")) {
			final Player player = (Player) sender;
			player.setHealth(0);
		}
		return false;
	}
}
