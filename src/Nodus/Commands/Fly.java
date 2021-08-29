package Nodus.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Fly implements CommandExecutor, Listener {
	public static ArrayList<String> flay;

	static {
		Fly.flay = new ArrayList<String>();
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String cmdLabel,
			final String[] args) {
		if (sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("fly")) {
				final Player player = (Player) sender;
				if (args.length == 0 && sender.hasPermission("nodus.fly")) {
					if (!player.getAllowFlight()) {
						player.sendMessage(ChatColor.GREEN + "§bFly Ligado");
						Fly.flay.add(player.getName());
						player.setAllowFlight(true);
					} else {
						player.sendMessage(ChatColor.GREEN + "Fly Desligado");
						Fly.flay.remove(player.getName());
						player.setAllowFlight(false);
					}
				}
				if (args.length == 1 && sender.hasPermission("nodus.fly")) {
					final Player player2 = Bukkit.getPlayer(args[0]);
					if (player2 == null) {
						sender.sendMessage(ChatColor.RED + "Jogador Offline");
						return true;
					}
					if (!player.getAllowFlight()) {
						sender.sendMessage(ChatColor.GREEN + "Voo Habilitado para " + player2.getName());
						player2.setAllowFlight(true);
						Fly.flay.add(player2.getName());
						player2.sendMessage(ChatColor.GREEN + "Voo Habilitado por " + player.getName());
					} else {
						sender.sendMessage(ChatColor.GREEN + "Voo Desabilitado para " + player2.getName());
						player.setAllowFlight(false);
						Fly.flay.remove(player2.getName());
						player2.sendMessage(ChatColor.GREEN + "Voo Desabilitado por " + player.getName());
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Voce deve ser um usuario!");
		}
		return false;
	}

	public void voarpralongedoplayer(final PlayerMoveEvent p) {
		if (Fly.flay.contains(p.getEventName())) {
			for (int i = 0; i < p.getPlayer().getNearbyEntities(8.0, 8.0, 8.0).size(); i++)
				p.getPlayer().setVelocity(new Vector(0, 3, 0));
		}
	}
}
