package Nodus.Commands;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class LimparChatV1 implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Voce nao e um jogador!");
            return false;
        }
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("limparchat")) {
            if (p.hasPermission("nodus.limparchat")) {
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
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage("");
                Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "§bO Chat foi Limpo por  §a§o" + p.getName());
            }
            return false;
        }
        return false;
    }
}
