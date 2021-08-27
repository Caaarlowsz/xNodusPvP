package Nodus.Commands;

import org.bukkit.event.*;
import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class Fake implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Fake(final Main main) {
        Fake.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final Player p = (Player)sender;
        if (commandLabel.equalsIgnoreCase("fake")) {
            if (p.hasPermission("nodus.fake")) {
                if (args.length == 0) {
                    p.setDisplayName(p.getName());
                    p.chat("/disguise un");
                    p.setCustomName(p.getName());
                    p.setPlayerListName(p.getName());
                    p.sendMessage("�5Nick:�b " + p.getPlayer().getName());
                    return true;
                }
                String nicks = args[0];
                nicks = nicks.replaceAll("&", "�");
                Fake.plugin.getConfig().set(p.getName(), (Object)nicks);
                Fake.plugin.saveConfig();
                p.setPlayerListName(nicks);
                p.setCustomName(nicks);
                p.sendMessage(ChatColor.AQUA + "�7Nick:�b " + nicks);
                p.chat("/disguise player " + nicks);
            }
            else {
                p.sendMessage("");
            }
        }
        return false;
    }
}
