package Nodus.Warp;

import org.bukkit.event.*;
import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class SetTextura implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public SetTextura(final Main main) {
        SetTextura.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("settexturas") && sender instanceof Player) {
            if (!sender.hasPermission("nodus.set")) {
                final Player p = (Player)sender;
                p.sendMessage("§cVoce nao tem permissao!");
            }
            if (sender.hasPermission("nodus.set")) {
                final Player p = (Player)sender;
                SetTextura.plugin.getConfig().set("textura.x", (Object)p.getLocation().getX());
                SetTextura.plugin.getConfig().set("textura.y", (Object)p.getLocation().getY());
                SetTextura.plugin.getConfig().set("textura.z", (Object)p.getLocation().getZ());
                SetTextura.plugin.getConfig().set("textura.pitch", (Object)p.getLocation().getPitch());
                SetTextura.plugin.getConfig().set("textura.yaw", (Object)p.getLocation().getYaw());
                SetTextura.plugin.getConfig().set("textura.world", (Object)p.getLocation().getWorld().getName());
                SetTextura.plugin.saveConfig();
                p.sendMessage("§a [!] textura setada com sucesso");
            }
            return true;
        }
        return false;
    }
}
