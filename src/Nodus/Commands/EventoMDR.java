package Nodus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class EventoMDR implements CommandExecutor, Listener {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Voce nao e um jogador!");
			return false;
		}
		final Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("eventomdr")) {
			if (p.hasPermission("nodus.evento")) {
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "§l____________Aviso___________");
				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "§l        [Evento MDR]  ");
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "§l           /MDR              ");
				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "§l_____________________________");
			}
			return false;
		}
		return false;
	}
}
