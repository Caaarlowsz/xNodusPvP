package Nodus.Event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CopyOfCommandChat implements CommandExecutor {
	public static boolean chat;

	static {
		CopyOfCommandChat.chat = true;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (sender.hasPermission("nodus.chat")) {
			if (args.length > 0) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("off")) {
						CopyOfCommandChat.chat = false;
						Bukkit.getServer()
								.broadcastMessage(String.valueOf(ChatColor.RED.toString()) + "O chat foi desativado");
						Player[] onlinePlayers;
						for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
							final Player p2 = onlinePlayers[i];
							if (p2.hasPermission("nodus.chat")) {
								p2.sendMessage(ChatColor.GRAY + sender.getName() + " Desativou o chat.");
							}
						}
					} else if (args[0].equalsIgnoreCase("on")) {
						CopyOfCommandChat.chat = true;
						Bukkit.getServer()
								.broadcastMessage(String.valueOf(ChatColor.GREEN.toString()) + "O chat foi Ativado");
						Player[] onlinePlayers2;
						for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length,
								j = 0; j < length2; ++j) {
							final Player p2 = onlinePlayers2[j];
							if (p2.hasPermission("nodus.chat")) {
								p2.sendMessage(ChatColor.GRAY + sender.getName() + " ativou o chat.");
							}
						}
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Use: /chat (on | off)");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Use: /chat (on | off)");
			}
		}
		return true;
	}
}
