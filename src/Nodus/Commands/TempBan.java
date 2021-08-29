package Nodus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import Nodus.Main.Main;

public class TempBan implements CommandExecutor {
	private Main plugin;

	public TempBan(final Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		final Player p = (Player) sender;
		if (cmd.equalsIgnoreCase("tempban") && p.hasPermission("nodus.tempban")) {
			if (args.length >= 2) {
				final Player target = p.getServer().getPlayer(args[0]);
				if (target != null) {
					if (!target.isOp()) {
						target.setBanned(true);
						target.kickPlayer(args[1]);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
								(Runnable) new Runnable() {
									@Override
									public void run() {
										target.setBanned(false);
									}
								}, 24000L);
					}
					p.sendMessage(ChatColor.RED + "Voce baniu " + target.getName());
				} else {
					p.sendMessage(ChatColor.DARK_RED + "Erro: " + ChatColor.RED + "Esse jogador nao pode ser banido.");
				}
			} else {
				p.sendMessage(ChatColor.DARK_RED + "Erro: " + ChatColor.RED + "Jogador inexistente.");
			}
		} else {
			p.sendMessage(ChatColor.DARK_RED + "Erro: " + ChatColor.RED + "Use /" + cmd + " <jogador> <motivo>");
		}
		return true;
	}
}
