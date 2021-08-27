package Nodus.Kits;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import java.util.*;
import org.bukkit.*;

public class Critical implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Critical(final Main main) {
        Critical.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("critical")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.critical")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Critical");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.critical.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Critical");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Critical.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Critical.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    public void dano(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final Player d = (Player)e.getDamager();
            if (Main.critical.contains(d.getName())) {
                final Random r = new Random();
                final int c = r.nextInt(100);
                if (c <= 30) {
                    e.setDamage(e.getDamage() + 4.0);
                    p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, (Object)Material.REDSTONE_BLOCK, 10);
                    p.sendMessage(ChatColor.RED + "Voce recebeu um golpe critico de " + ChatColor.DARK_RED + d.getName());
                }
            }
        }
    }
}
