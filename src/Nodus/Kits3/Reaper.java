package Nodus.Kits3;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.potion.*;
import org.bukkit.event.*;

public class Reaper implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Reaper(final Main main) {
        Reaper.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("reaper")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.reaper")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "Reaper");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.reaper.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    Habilidade.setAbility(p, "Reaper");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.WOOD_HOE) });
                    Main.giveSoup(p, 34);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Reaper.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Reaper.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void OnClick(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            final Player damager = (Player)e.getDamager();
            final Player victim = (Player)e.getEntity();
            if (Main.reaper.contains(damager.getName())) {
                Main.reaper.add(damager.getName());
                if (damager.getInventory().getItemInHand().getType() == Material.WOOD_HOE) {
                    victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 3));
                }
            }
        }
    }
}
