package Nodus.Kits;

import org.bukkit.command.*;
import Nodus.Event.*;
import Nodus.Main.*;
import org.bukkit.inventory.*;
import Nodus.Warp.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.event.*;

public class Stomper implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Stomper(final Main main) {
        Stomper.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
        final Player p = (Player)sender;
        if (cmd.equalsIgnoreCase("stomper")) {
            if (Main.usandokit.contains(p.getName())) {
                p.sendMessage("UM KIT POR VIDA");
                return true;
            }
            if (!p.hasPermission("kit.Stomper")) {
                p.sendMessage("SEM PERMISSAO");
                return true;
            }
            Main.usandokit.add(p.getName());
            Main.stomper.add(p.getName());
            p.sendMessage(String.valueOf(ServerName.nomedokit) + "Stomper");
            Habilidade.setAbility(p, "Stomper");
            p.setGameMode(GameMode.SURVIVAL);
            p.getInventory().setArmorContents((ItemStack[])null);
            p.getInventory().clear();
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
            Main.giveSoup(p, 34);
            p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
            SetArena.TeleportArenaRandom(p);
        }
        return false;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerStomp(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        if (Main.stomper.contains(p.getName())) {
            for (final Entity ent : p.getNearbyEntities(2.0, 2.0, 2.0)) {
                if (ent instanceof Player) {
                    final Player plr = (Player)ent;
                    if (e.getDamage() <= 4.0) {
                        e.setCancelled(true);
                        return;
                    }
                    if (plr.isSneaking()) {
                        plr.damage(6.0, (Entity)p);
                        plr.sendMessage(ChatColor.GRAY + "Voce Foi pisoteado Por: " + ChatColor.AQUA + p.getName());
                    }
                    else {
                        plr.damage(e.getDamage(), (Entity)p);
                        plr.sendMessage(ChatColor.GRAY + "Voce Foi Pisoteado Por: " + ChatColor.AQUA + p.getName());
                        plr.damage((double)p.getFallDistance());
                    }
                }
            }
            e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
            e.setDamage(4.0);
        }
    }
}
