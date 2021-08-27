package Nodus.Commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class StaffChat implements CommandExecutor
{
    public String[] aliases;
    public String description;
    
    public StaffChat() {
        this.aliases = new String[] { "staffc", "sc", "stc", "staffchat", "staffct", "schat", "stchat", "stfc" };
        this.description = "Chat da staff.";
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player player = (Player)sender;
        if (player.hasPermission("nodus.sc")) {
            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                builder.append(args[i]).append(" ");
            }
            final String message = builder.toString();
            if (sender instanceof Player) {
                if (args.length > 0) {
                    Player[] onlinePlayers;
                    for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, j = 0; j < length; ++j) {
                        final Player p = onlinePlayers[j];
                        if (p.hasPermission("nodus.sc")) {
                            p.sendMessage(ChatColor.AQUA + "[SC]" + player.getName() + ChatColor.RESET + " >>> " + ChatColor.GRAY + message);
                        }
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Voce precisa escrever uma mensagem! (Uso correto: '/sc <mensagem>')");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "So players podem usar este comando!");
            }
        }
        else {
            player.sendMessage("sem permissao");
        }
        return false;
    }
}
