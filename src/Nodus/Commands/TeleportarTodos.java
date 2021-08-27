package Nodus.Commands;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;

public class TeleportarTodos implements CommandExecutor, Listener
{
    public void tpall(final Player p, final Player p2) {
        p2.teleport(p.getLocation());
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpall")) {
            final Player p = (Player)sender;
            if (p.hasPermission("nodus.tpall")) {
                p.sendMessage(ChatColor.AQUA + "Todos os jogadores foram teleportados!");
                Player[] onlinePlayers;
                for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                    final Player p2 = onlinePlayers[i];
                    this.tpall(p, p2);
                }
            }
        }
        return false;
    }
}
