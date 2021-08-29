package Nodus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import Nodus.InventariosFake.MenuTags;
import Nodus.Main.TagLinda;

public class Tag implements Listener, CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("tag")) {
			final Player p = (Player) sender;
			if (args.length == 0) {
				MenuTags.guitags(p);
				return false;
			}
			if (args[0].equalsIgnoreCase("default")) {
				TagLinda.removeAbility(p);
				p.sendMessage(ChatColor.GREEN + "Tag Alterada Para Default");
				p.setDisplayName("§7 " + p.getName() + "§f");
				p.setPlayerListName("§7" + p.getName());
			} else if (args[0].equalsIgnoreCase("pro")) {
				if (p.hasPermission("tag.pro")) {
					TagLinda.setAbility(p, "§6Pro");
					p.sendMessage(ChatColor.GREEN + "Tag Alterada Para Pro");
					p.setDisplayName("§6Pro " + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§6" + p.getName());
				} else {
					p.sendMessage("§c Voce Nao Possui Esse Rank");
				}
			} else if (args[0].equalsIgnoreCase("youtuber")) {
				if (p.hasPermission("tag.youtuber")) {
					TagLinda.setAbility(p, "§bYoTuber");
					p.sendMessage(ChatColor.GREEN + "Tag Alterada Para Youtuber");
					p.setDisplayName("§BYouTuber  " + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§b§o" + p.getName());
				} else {
					p.sendMessage("§c Voce Nao Possui Esse Rank");
				}
			} else if (args[0].equalsIgnoreCase("mod")) {
				if (p.hasPermission("tag.mod")) {
					TagLinda.setAbility(p, "§5Mod");
					p.sendMessage(ChatColor.GREEN + "Tag Alterada Para Mod");
					p.setDisplayName("§5Mod  " + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§5" + p.getName());
				} else {
					p.sendMessage("§c Voce Nao Possui Esse Rank");
				}
			} else if (args[0].equalsIgnoreCase("trial")) {
				if (p.hasPermission("tag.trial")) {
					TagLinda.setAbility(p, "§dTrial");
					p.sendMessage("Tag Alterada Para Trial");
					p.setDisplayName("§d§o Trial " + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§d" + p.getName());
				} else {
					p.sendMessage("§c Voce Nao Possui Esse Rank");
				}
			} else if (args[0].equalsIgnoreCase("admin")) {
				if (p.hasPermission("tag.admin")) {
					TagLinda.setAbility(p, "§cAdmin");
					p.sendMessage(ChatColor.GREEN + "Tag Alterada Para Admin");
					p.setDisplayName("§c Admin §o" + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§c§o" + p.getName());
				} else {
					p.sendMessage("§cVoce Nao Possui Esse Rank");
				}
			} else if (args[0].equalsIgnoreCase("dono")) {
				if (p.hasPermission("tag.dono")) {
					TagLinda.setAbility(p, "§4Dono");
					p.sendMessage(ChatColor.GREEN + "Tag Alterada Para Dono");
					p.setDisplayName("§4 Dono §o" + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§4§o" + p.getName());
				} else {
					p.sendMessage("§cVoce Nao Possui Esse Rank");
				}
			} else if (args[0].equalsIgnoreCase("dev")) {
				if (p.getName().equals("_xNodusPvP")) {
					TagLinda.setAbility(p, "§3Dev");
					p.sendMessage(ChatColor.GREEN + "Tag Alterada Para Dev");
					p.setDisplayName("§3 Dev §o" + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§3§o" + p.getName());
				} else {
					p.sendMessage("§cVoce Nao Possui Esse Rank");
				}
			} else if (args[0].equalsIgnoreCase("mvp")) {
				if (p.hasPermission("tag.mvp")) {
					TagLinda.setAbility(p, "§1MVP");
					p.sendMessage(ChatColor.GREEN + "Tag Alterada Para MVP");
					p.setDisplayName("§1 MVP §o" + p.getName() + ChatColor.WHITE);
					p.setPlayerListName("§1§l" + p.getName());
				} else {
					p.sendMessage("§cVoce Nao Possui Esse Rank");
				}
			}
		}
		return false;
	}

	public static String getShortStr(final String s) {
		if (s.length() == 16) {
			final String shorts = s.substring(0, s.length() - 4);
			return shorts;
		}
		if (s.length() == 15) {
			final String shorts = s.substring(0, s.length() - 3);
			return shorts;
		}
		if (s.length() == 14) {
			final String shorts = s.substring(0, s.length() - 2);
			return shorts;
		}
		if (s.length() == 13) {
			final String shorts = s.substring(0, s.length() - 1);
			return shorts;
		}
		if (s.length() == 12) {
			final String shorts = s.substring(0, s.length() - 1);
			return shorts;
		}
		if (s.length() == 11) {
			final String shorts = s.substring(0, s.length() - 1);
			return shorts;
		}
		return s;
	}
}
