package Nodus.Kits2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import Nodus.Main.Main;

public class Neji implements Listener, CommandExecutor {
	public static Main plugin;

	public Neji(final Main main) {
		Neji.plugin = main;
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("Neji") && !Main.usandokit.contains(p.getName()) && p.hasPermission("kit.Neji")) {
			p.sendMessage("§4Kit em manutencao");
		}
		return false;
	}
}
