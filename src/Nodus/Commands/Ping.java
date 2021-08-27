package Nodus.Commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_7_R1.entity.*;
import org.bukkit.*;

public class Ping implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String c, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final int ping = ((CraftPlayer)p).getHandle().ping;
            if (c.equalsIgnoreCase("ping")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.GREEN + "�bSeu Ping \u00e9 de: �7" + ping + " �bMs");
                }
                else if (p.getServer().getPlayer(args[0]) != null) {
                    final String player2 = args[0];
                    final Player target = Bukkit.getServer().getPlayer(args[0]);
                    final int ping2 = ((CraftPlayer)target).getHandle().ping;
                    p.sendMessage(ChatColor.AQUA + "O Jogador " + player2 + " tem um ping de: " + ChatColor.WHITE + ping2 + ChatColor.GREEN + " ms!");
                }
                else {
                    final String player2 = args[0];
                    p.sendMessage(ChatColor.RED + player2 + " Nao esta Online!");
                }
            }
        }
        return false;
    }
}
