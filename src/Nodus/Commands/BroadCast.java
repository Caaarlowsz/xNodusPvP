package Nodus.Commands;

import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class BroadCast implements CommandExecutor
{
    public static Main plugin;
    
    public BroadCast(final Main main) {
        BroadCast.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if ((label.equalsIgnoreCase("bc") || label.equalsIgnoreCase("broadcast")) && p.hasPermission("nodus.bc")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Use /bc (Mensagem)");
                return true;
            }
            String msg = "";
            final String[] arrayOfStrings = args;
            for (int j = args.length, i = 0; i < j; ++i) {
                final String msg2 = arrayOfStrings[i];
                msg = String.valueOf(msg) + msg2 + " ";
            }
            Bukkit.broadcastMessage("§b[§f" + ChatColor.translateAlternateColorCodes('&', BroadCast.plugin.getConfig().getString("NomeServer")) + "§b] §f§o§l" + msg.replace("&", "§"));
        }
        return false;
    }
}
