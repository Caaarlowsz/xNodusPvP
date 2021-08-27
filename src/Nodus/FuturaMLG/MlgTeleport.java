package Nodus.FuturaMLG;

import org.bukkit.event.*;
import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class MlgTeleport implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public MlgTeleport(final Main main) {
        MlgTeleport.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (p.hasPermission("nodus.set") && label.equalsIgnoreCase("setmlg")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Use: /setmlg setar");
                return true;
            }
            if (args[0].equalsIgnoreCase("setar")) {
                Main.config = Main.getPlugin().getConfig();
                p.sendMessage(ChatColor.GREEN + "Setado");
                Main.config.set("MLG.FACIL.X", (Object)p.getLocation().getX());
                Main.config.set("MLG.FACIL.Y", (Object)p.getLocation().getY());
                Main.config.set("MLG.FACIL.Z", (Object)p.getLocation().getZ());
                final World w = p.getWorld();
                Main.config.set("MLG.FACIL.PITCH", (Object)p.getLocation().getPitch());
                Main.config.set("MLG.FACIL.YAW", (Object)p.getLocation().getYaw());
                Main.config.set("MLG.FACIL.WORLD", (Object)p.getLocation().getWorld().getName());
                Main.plugin.saveConfig();
                Main.mlg.add(p.getName());
            }
        }
        return false;
    }
}
