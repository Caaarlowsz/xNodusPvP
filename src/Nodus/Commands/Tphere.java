package Nodus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Tphere implements CommandExecutor {
	public String[] aliases;
	public String description;

	public Tphere() {
		this.aliases = new String[] { "s", "tph", "stp", "tphere" };
		this.description = "Teleporte jogadores para o seu local!";
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player player = (Player) sender;
		if (player.hasPermission("nodus.tphere")) {
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Use: /s <jogador>");
				return true;
			}
			if (args.length == 1) {
				final Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null || !(target instanceof Player)) {
					player.sendMessage(ChatColor.RED + "Este jogador nao foi encontrado!");
					return true;
				}
				target.teleport((Entity) player);
				Player[] onlinePlayers;
				for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
					final Player p1 = onlinePlayers[i];
					if (p1.hasPermission("kit.admin") || p1.isOp() || p1.getName().equals("Rzs_SrIski8")) {
						p1.sendMessage(ChatColor.GRAY + target.getName() + " foi teleportado para " + player.getName());
					}
				}
				return true;
			} else if (args.length > 1) {
				player.sendMessage(ChatColor.RED + "Use: /s <jogador>");
				return true;
			}
		}
		return false;
	}
}
