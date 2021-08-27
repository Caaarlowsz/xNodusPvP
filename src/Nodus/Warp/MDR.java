package Nodus.Warp;

import org.bukkit.event.*;
import Nodus.Main.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.plugin.*;
import org.bukkit.*;

public class MDR implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public MDR(final Main main) {
        MDR.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("mdr")) {
            final Player p = (Player)sender;
            final World w = Bukkit.getServer().getWorld(MDR.plugin.getConfig().getString("mdr.world"));
            final double x = MDR.plugin.getConfig().getDouble("mdr.x");
            final double y = MDR.plugin.getConfig().getDouble("mdr.y");
            final double z = MDR.plugin.getConfig().getDouble("mdr.z");
            final Location lobby = new Location(w, x, y, z);
            lobby.setPitch((float)MDR.plugin.getConfig().getDouble("mdr.pitch"));
            lobby.setYaw((float)MDR.plugin.getConfig().getDouble("mdr.yaw"));
            p.getInventory().clear();
            p.setHealthScale(1.0);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 100));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 100));
            p.sendMessage("§3§l > §7Teleportando em 5 segundos <");
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MDR.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.teleport(lobby);
                    p.getInventory().clear();
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage("§a[§3!§a]  §aTeleportado!");
                    p.setHealthScale(20.0);
                    Main.removeAbility(p);
                    Main.usandokit.add(p.getName());
                    p.getInventory().setArmorContents((ItemStack[])null);
                    p.updateInventory();
                    for (final PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                }
            }, 90L);
            return false;
        }
        return false;
    }
}
