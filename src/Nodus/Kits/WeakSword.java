package Nodus.Kits;

import org.bukkit.command.*;
import Nodus.Event.*;
import org.bukkit.inventory.*;
import Nodus.Main.*;
import Nodus.Warp.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.potion.*;
import org.bukkit.event.*;

public class WeakSword implements Listener, CommandExecutor
{
    public static Main plugin;
    
    public WeakSword(final Main main) {
        WeakSword.plugin = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("weaksword")) {
            if (!Main.usandokit.contains(p.getName())) {
                if (p.hasPermission("kit.weaksword")) {
                    p.sendMessage(String.valueOf(ServerName.nomedokit) + "WeakSword");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 4.0f, 4.0f);
                    Main.usandokit.add(p.getName());
                    Main.weaksword.add(sender.getName());
                    p.getInventory().clear();
                    final ItemStack espada = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta espadameta = espada.getItemMeta();
                    espadameta.setDisplayName("�cSword");
                    Habilidade.setAbility(p, "WeakSword");
                    p.getInventory().addItem(new ItemStack[] { espada });
                    Main.giveSoup(p, 35);
                    p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                    SetArena.TeleportArenaRandom(p);
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', WeakSword.plugin.getConfig().getString("Sem_Permiss\u00c3�o_Kit")));
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', WeakSword.plugin.getConfig().getString("Um_Kit_Por_Vida")));
            }
            return true;
        }
        return false;
    }
    
    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity entity = (LivingEntity)e.getEntity();
        final Player p = (Player)e.getDamager();
        if (!Main.weaksword.contains(p.getName())) {
            return;
        }
        final Random rand = new Random();
        final int percent = rand.nextInt(100);
        if (percent <= 33) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 0));
        }
    }
}