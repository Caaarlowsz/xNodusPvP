package Nodus.Commands;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class Gamemode implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player player = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("gm") && sender.hasPermission("nodus.gm")) {
            if (args.length != 1) {
                player.sendMessage("§aGm 0 - Gm 1");
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("0")) {
                    player.sendMessage("§7Voce entrou em modo §4Survival");
                    player.setGameMode(GameMode.SURVIVAL);
                }
                if (args[0].equalsIgnoreCase("1")) {
                    player.sendMessage("§7Voce entrou em modo §4Criativo");
                    player.setGameMode(GameMode.CREATIVE);
                }
                return true;
            }
        }
        return false;
    }
}
