package Nodus.Commands;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class EventoRDM implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Voce nao e um jogador!");
            return false;
        }
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("eventordm")) {
            if (p.hasPermission("nodus.evento")) {
                final Player player = (Player)sender;
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
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "§l____________Aviso___________");
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "§l        [Evento RDM]  ");
                Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + "§l           /RDM             ");
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "§l_____________________________");
            }
            return false;
        }
        return false;
    }
}
