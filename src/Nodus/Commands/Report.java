package Nodus.Commands;

import org.bukkit.event.*;
import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class Report implements CommandExecutor, Listener
{
    public Main plugin;
    
    public Report(final Main main) {
        this.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("report")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "[!] Use: " + ChatColor.AQUA + "/report (Nick ) (Denuncia)");
            }
            if (args.length > 0) {
                sender.sendMessage(ChatColor.GREEN + "Report Enviado!");
                final StringBuilder string = new StringBuilder();
                for (int i = 0; i < args.length; ++i) {
                    string.append(String.valueOf(args[i]) + " ");
                }
                final String mensagem = string.toString();
                Player[] onlinePlayers;
                for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, j = 0; j < length; ++j) {
                    final Player staff = onlinePlayers[j];
                    if (staff.hasPermission("kitpvp.report")) {
                        staff.sendMessage("                 §4§l[§bReport§4§l]");
                        staff.sendMessage(ChatColor.GRAY + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        staff.sendMessage(ChatColor.LIGHT_PURPLE + "REPORT DE: " + ChatColor.AQUA + sender.getName());
                        staff.sendMessage(ChatColor.LIGHT_PURPLE + "MOTIVO: " + ChatColor.AQUA + mensagem);
                        staff.sendMessage(ChatColor.GRAY + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    }
                }
            }
        }
        return false;
    }
}
