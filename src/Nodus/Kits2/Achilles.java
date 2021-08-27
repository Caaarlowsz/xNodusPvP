package Nodus.Kits2;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Achilles implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Achilles(final Main main) {
        Achilles.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Achilles")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Achilles")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Achilles");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.achilles.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    Habilidade.setAbility(p, "Achilles");
                    espadameta.setDisplayName("§cSword");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Achilles.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Achilles.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onDamageAchilles(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player) {
            final Player damager = (Player)e.getDamager();
            if (Main.achilles.contains(damager.getName()) && damager.getItemInHand() != null) {
                if (damager.getItemInHand().getType() == Material.GOLD_SWORD) {
                    e.setDamage(1.0);
                }
                else if (damager.getItemInHand().getType() == Material.STONE_SWORD) {
                    e.setDamage(2.0);
                }
                else if (damager.getItemInHand().getType() == Material.IRON_SWORD) {
                    e.setDamage(3.0);
                }
                else if (damager.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                    e.setDamage(4.0);
                }
                else if (damager.getItemInHand().getType() == Material.WOOD_SWORD) {
                    e.setDamage(7.0);
                }
            }
        }
    }
}
