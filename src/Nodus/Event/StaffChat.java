package Nodus.Event;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import Nodus.Main.Main;

public class StaffChat implements Listener, CommandExecutor {
	public static Main plugin;
	public static ArrayList<String> staff;

	static {
		StaffChat.staff = new ArrayList<String>();
	}

	public StaffChat(final Main main) {
		StaffChat.plugin = main;
	}

	@EventHandler
	public void onChat(final AsyncPlayerChatEvent e) {
		final Player p = e.getPlayer();
		Player[] arrayOfPlayer;
		for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
			final Player online = arrayOfPlayer[i];
			if (this.isStaffChatting(p)) {
				e.setCancelled(true);
				if (online.hasPermission("mad.sc") || this.isStaffChatting(online)) {
					online.sendMessage(
							ChatColor.GRAY + "[§cStaff] " + p.getDisplayName() + "§a " + "§6" + e.getMessage());
				}
			}
		}
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("sc") && p.hasPermission("mad.sc")) {
			if (this.isStaffChatting(p)) {
				StaffChat.staff.remove(p.getName());
				p.sendMessage(ChatColor.RED + "Voce saiu do chat da staff.");
			} else {
				StaffChat.staff.add(p.getName());
				p.sendMessage(ChatColor.GREEN + "Voce entrou no chat da staff.");
			}
		}
		return true;
	}

	public boolean isStaffChatting(final Player player) {
		return StaffChat.staff.contains(player.getName());
	}
}
