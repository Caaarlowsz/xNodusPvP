package Nodus.Warp;

import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class SetMain implements CommandExecutor
{
    public static Main plugin;
    
    public SetMain(final Main main) {
        SetMain.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("setmain") && sender instanceof Player) {
            if (!sender.hasPermission("nodus.set")) {
                final Player p1 = (Player)sender;
                p1.sendMessage("�cVoce nao tem permissao!");
            }
            if (sender.hasPermission("nodus.set")) {
                final Player p1 = (Player)sender;
                SetMain.plugin.getConfig().set("main.x", (Object)p1.getLocation().getX());
                SetMain.plugin.getConfig().set("main.y", (Object)p1.getLocation().getY());
                SetMain.plugin.getConfig().set("main.z", (Object)p1.getLocation().getZ());
                SetMain.plugin.getConfig().set("main.pitch", (Object)p1.getLocation().getPitch());
                SetMain.plugin.getConfig().set("main.yaw", (Object)p1.getLocation().getYaw());
                SetMain.plugin.getConfig().set("main.world", (Object)p1.getLocation().getWorld().getName());
                SetMain.plugin.saveConfig();
                p1.sendMessage("�a [!] Warp Main setada com sucesso");
            }
            return false;
        }
        return false;
    }
}
