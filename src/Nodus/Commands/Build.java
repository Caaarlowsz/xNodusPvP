package Nodus.Commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import Nodus.Main.Main;

public class Build implements Listener, CommandExecutor {
	public static ArrayList<String> build;

	static {
		Build.build = new ArrayList<String>();
	}

	@EventHandler
	public void aoColocarBlocos(final BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		if (!Build.build.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void aoQuebrarBlocos(final BlockBreakEvent e) {
		final Player p = e.getPlayer();
		if (!Build.build.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (p.hasPermission("nodus.build") && label.equalsIgnoreCase("build")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Use: /build on/off");
				return true;
			}
			if (args[0].equalsIgnoreCase("on")) {
				p.sendMessage("§aBUILT ON");
				Build.build.add(p.getName());
				Main.plugin.saveConfig();
			}
			if (args[0].equalsIgnoreCase("off")) {
				p.sendMessage("§cBUILT OFF");
				Build.build.remove(p.getName());
			}
		}
		return false;
	}
}
