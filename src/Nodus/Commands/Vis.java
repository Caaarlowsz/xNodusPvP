package Nodus.Commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import n.*;
import org.bukkit.*;

public class Vis implements CommandExecutor
{
    public String[] aliases;
    public String description;
    
    public Vis() {
        this.aliases = new String[] { "vis" };
        this.description = "Fique visivel!";
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        Player p;
        if (sender instanceof Player) {
            p = (Player)sender;
        }
        else {
            p = null;
        }
        if (sender.hasPermission("nodus.invis")) {
            if (Vanish.vanished.contains(p)) {
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.vanished.remove(p);
                Vanish.updateVanished();
                Player[] onlinePlayers;
                for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                    final Player p2 = onlinePlayers[i];
                    if (p2.hasPermission("kit.admin") || p2.isOp()) {
                        p2.sendMessage(ChatColor.GRAY + p.getName() + " ficou visivel.");
                    }
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce ja esta VISIVEL");
            }
        }
        return true;
    }
}
