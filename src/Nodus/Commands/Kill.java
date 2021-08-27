package Nodus.Commands;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class Kill implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
        final Player p = (Player)sender;
        if (cmd.equalsIgnoreCase("suicide")) {
            final Player player = (Player)sender;
            player.setHealth(0);
        }
        return false;
    }
}
