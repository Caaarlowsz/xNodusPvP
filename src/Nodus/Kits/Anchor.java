package Nodus.Kits;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class Anchor implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Anchor(final Main main) {
        Anchor.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("anchor")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.anchor")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Anchor");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.anchor.add(sender.getName());
                    p.getInventory().clear();
                    p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Habilidade.setAbility(p, "Anchor");
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Anchor.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Anchor.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerHitAnchor(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        final Player a = (Player)e.getDamager();
        if (Main.anchor.contains(p.getName())) {
            p.setVelocity(new Vector());
            p.playSound(p.getLocation(), Sound.ZOMBIE_METAL, 4.0f, 4.0f);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Anchor.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.setVelocity(new Vector());
                }
            }, 1L);
        }
        if (Main.anchor.contains(a.getName())) {
            a.playSound(a.getLocation(), Sound.ZOMBIE_METAL, 4.0f, 4.0f);
            p.setVelocity(new Vector());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Anchor.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.setVelocity(new Vector());
                }
            }, 1L);
        }
    }
}
