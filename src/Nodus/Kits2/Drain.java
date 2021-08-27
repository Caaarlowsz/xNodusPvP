package Nodus.Kits2;

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

public class Drain implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public Drain(final Main main) {
        Drain.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("Drain")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.Drain")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + " Drain");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.viadin.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("§cSword");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Habilidade.setAbility(p, "Drain");
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Drain.plugin.getConfig().getString("Sem_Permiss\u00c3£o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Drain.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onKill(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player k = e.getEntity().getKiller();
            if (Main.viadin.contains(k.getName())) {
                k.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 300, 4), true);
                k.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1000), true);
            }
        }
    }
}
