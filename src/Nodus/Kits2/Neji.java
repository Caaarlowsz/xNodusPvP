package Nodus.Kits2;

import org.bukkit.event.*;
import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class Neji implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Neji(final Main main) {
        Neji.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Neji") && !Main.usandokit.contains(p.getName()) && p.hasPermission("kit.Neji")) {
            p.sendMessage("§4Kit em manutencao");
        }
        return false;
    }
}
